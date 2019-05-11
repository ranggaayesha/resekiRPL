package android.reseki.com.reseki;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    Context context;
    List<Post> posts;

    public PopularAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Post post = posts.get(position);
        holder.recentUsername.setText(post.getUsername());
        holder.recentPhotoTitle.setText(post.getPhotoTitle());
        holder.recentPhotoDesc.setText(post.getPhotoDesc());

        Picasso.get().load(post.getPhoto()).into(holder.recentPhoto);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    // INNER CLASS
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView recentUsername, recentPhotoDesc, recentPhotoTitle;
        ImageView recentPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            recentUsername = itemView.findViewById(R.id.popularUsername);
            recentPhotoTitle = itemView.findViewById(R.id.popularPhotoTitle);
            recentPhotoDesc = itemView.findViewById(R.id.popularPhotoDesc);
            recentPhoto = itemView.findViewById(R.id.popularPhoto);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Post post = posts.get(position);
            String postId = post.getPostId();
            String title = post.getPhotoTitle();
            String desc = post.getPhotoDesc();
            String photoUrl = post.getPhoto();

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("desc", desc);
            intent.putExtra("photo", photoUrl);
            intent.putExtra("id", postId);
            context.startActivity(intent);
        }
    }
}
