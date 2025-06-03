package com.midterm.pos_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    private Context context;
    private List<CoffeeItem> coffeeList;
    private OnCoffeeClickListener listener;

    public interface OnCoffeeClickListener {
        void onAddClick(CoffeeItem coffeeItem);
        void onHeartClick(CoffeeItem coffeeItem);
        void onSizeClick(CoffeeItem coffeeItem, String size);
    }

    public CoffeeAdapter(Context context, List<CoffeeItem> coffeeList, OnCoffeeClickListener listener) {
        this.context = context;
        this.coffeeList = coffeeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Đã sửa lỗi: Giả định rằng bạn sẽ tạo item_coffee.xml trong res/layout/
        View view = LayoutInflater.from(context).inflate(R.layout.item_coffee, parent, false);
        return new CoffeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        CoffeeItem coffeeItem = coffeeList.get(position);

        holder.txtCoffeeName.setText(coffeeItem.getName());
        holder.txtPrice.setText(coffeeItem.getPrice());

        // Lấy ID tài nguyên hình ảnh từ tên tài nguyên (String)
        int imageResId = context.getResources().getIdentifier(
                coffeeItem.getImageUrl(), "drawable", context.getPackageName());

        if (imageResId != 0) {
            holder.imgCoffee.setImageResource(imageResId);
        } else {
            // Đặt hình ảnh mặc định nếu không tìm thấy tài nguyên
            // Vui lòng tạo ic_default_coffee.xml (hoặc .png/.jpg) trong res/drawable/
            holder.imgCoffee.setImageResource(R.drawable.item_coffee_default);
        }

        // Đặt biểu tượng trái tim dựa trên trạng thái yêu thích
        if (coffeeItem.isFavorite()) {
            holder.imgHeart.setImageResource(R.drawable.ic_heart_filled);
        } else {
            holder.imgHeart.setImageResource(R.drawable.ic_heart_outline);
        }

        // Thiết lập lựa chọn kích thước
        setupSizeSelection(holder, coffeeItem);

        // Đặt các bộ lắng nghe sự kiện click
        holder.btnAdd.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAddClick(coffeeItem);
            }
        });

        holder.imgHeart.setOnClickListener(v -> {
            if (listener != null) {
                listener.onHeartClick(coffeeItem);
            }
        });
    }

    // This is a placeholder. You should use an image loading library like Glide or Picasso.
    private void loadImageFromFilePath(ImageView imageView, String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            File imgFile = new File(filePath);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
            } else {
                // Set a default image or handle the case where the file doesn't exist
                imageView.setImageResource(R.drawable.item_coffee_default); // You'll need a default image
            }
        } else {
            imageView.setImageResource(R.drawable.item_coffee_default); // You'll need a default image
        }
    }

    private void setupSizeSelection(CoffeeViewHolder holder, CoffeeItem coffeeItem) {
        holder.layoutSizes.removeAllViews();

        String[] availableSizes = coffeeItem.getAvailableSizes();
        if (availableSizes != null) {
            for (String size : availableSizes) {
                TextView sizeView = createSizeTextView(size, coffeeItem.getSelectedSize().equals(size));
                sizeView.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onSizeClick(coffeeItem, size);
                    }
                });
                holder.layoutSizes.addView(sizeView);
            }
        }
    }

    private TextView createSizeTextView(String size, boolean isSelected) {
        TextView textView = new TextView(context);
        textView.setText(size);
        textView.setTextSize(12);
        textView.setPadding(
                dpToPx(12), dpToPx(4),
                dpToPx(12), dpToPx(4)
        );

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMarginEnd(dpToPx(8));
        textView.setLayoutParams(params);

        if (isSelected) {
            textView.setTextColor(context.getResources().getColor(android.R.color.white));
            // Cần tạo size_selected_bg.xml trong res/drawable/
            textView.setBackgroundResource(R.drawable.size_selected_bg);
        } else {
            textView.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
            // Cần tạo size_unselected_bg.xml trong res/drawable/
            textView.setBackgroundResource(R.drawable.size_unselected_bg);
        }

        return textView;
    }

    private int dpToPx(int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    public static class CoffeeViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCoffee, imgHeart, btnAdd;
        TextView txtCoffeeName, txtPrice;
        LinearLayout layoutSizes;

        public CoffeeViewHolder(@NonNull View itemView) {
            super(itemView);
            // Cần đảm bảo các ID này tồn tại trong item_coffee.xml
            imgCoffee = itemView.findViewById(R.id.imgCoffee);
            imgHeart = itemView.findViewById(R.id.imgHeart);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            txtCoffeeName = itemView.findViewById(R.id.txtCoffeeName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            layoutSizes = itemView.findViewById(R.id.layoutSizes);
        }
    }
}