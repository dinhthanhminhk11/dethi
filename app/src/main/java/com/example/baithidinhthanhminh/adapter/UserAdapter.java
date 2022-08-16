package com.example.baithidinhthanhminh.adapter;

import android.graphics.Color;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.baithidinhthanhminh.R;
import com.example.baithidinhthanhminh.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> data;
    private Callback callback;

    public UserAdapter(List<User> data, Callback callback) {
        this.data = data;
        this.callback = callback;
    }

    public interface Callback {
        void onClick(User user);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = data.get(position);
        if (data != null) {
            holder.email.setText(user.getEmail());
            holder.id.setText(user.getId() + "");
            holder.firstName.setText(user.getFirst_name());
            holder.lastName.setText(user.getLast_name());
            if (user.getFirst_name().substring(0, 1).equals("E")) {
                holder.firstName.setTextColor(Color.GREEN);
            }
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage);
            Glide.with(holder.email.getContext()).load(user.getAvatar()).apply(options).into(holder.imageAvatar);
            holder.itemView.setOnClickListener(v -> {
                callback.onClick(user);
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageAvatar;
        private TextView id;
        private TextView email;
        private TextView firstName;
        private TextView lastName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageAvatar = (ImageView) itemView.findViewById(R.id.imageAvatar);
            id = (TextView) itemView.findViewById(R.id.id);
            email = (TextView) itemView.findViewById(R.id.email);
            firstName = (TextView) itemView.findViewById(R.id.first_name);
            lastName = (TextView) itemView.findViewById(R.id.last_name);

        }
    }
}
