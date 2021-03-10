package fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebook.R;


public class read_recycleadapter extends RecyclerView.Adapter<read_recycleadapter.mviewholder> {

    private Context context;
    private onitemlistener mlistener;
    int []book_image;
    String[]book_name;


    public read_recycleadapter(Context context,onitemlistener mlistener,int []book_image,String []book_name){
        this.context=context;
        this.mlistener=mlistener;
        this.book_image=book_image;
        this.book_name=book_name;

    }

    @NonNull
    @Override
    public mviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= View.inflate(context, R.layout.readbooks_item,null);
        return new mviewholder(item);
    }

    @Override
    public void onBindViewHolder(mviewholder holder, final int position) {
        holder.bookimage.setImageResource(book_image[position]);
        holder.bookname.setText(book_name[position]);
        holder.bookname.setTextColor(Color.parseColor("#333333"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return book_image.length;
    }

    class mviewholder extends RecyclerView.ViewHolder{
        private TextView bookname;
        private ImageView bookimage;
        public mviewholder(@NonNull View itemView) {
            super(itemView);
            bookname=itemView.findViewById(R.id.read_name);
            bookimage=itemView.findViewById(R.id.read_image);
        }
    }
    public interface onitemlistener{
        void onClick(int positon);
    }

}
