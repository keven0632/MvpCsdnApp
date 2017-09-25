package keven.cihon.mvpcsdnapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import keven.cihon.mvpcsdnapp.R;
import keven.cihon.mvpcsdnapp.book.BookDetailActivity;
import keven.cihon.mvpcsdnapp.book.bean.Book;
import keven.cihon.mvpcsdnapp.utils.LogUtils;

/**
 * Created by zhengjian on 2017/9/25.
 */

public class HotBooksAdapter extends RecyclerView.Adapter<HotBooksAdapter.HotBooksViewHolder> {
    private List<Book> mBooksList=new ArrayList<>();
    private Context mContext;
    @LayoutRes
    private int layoutResId;

    public HotBooksAdapter(Context context, @LayoutRes int layoutResId) {
        this.mContext = context;
        this.layoutResId = layoutResId;
    }
    public HotBooksAdapter(Context context, @NonNull List<Book> booksList, @LayoutRes int layoutResId) {
        this.mContext = context;
        this.mBooksList = booksList;
        this.layoutResId = layoutResId;
    }


    @Override
    public HotBooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(layoutResId, parent, false);

        return new HotBooksViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotBooksViewHolder holder, int position) {
        if (holder == null) return;
        holder.updateBook(mBooksList.get(position));
    }

    public void setData(List<Book> list) {
        this.mBooksList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mBooksList.size();
    }

    public class HotBooksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView mIv_head;
        private final TextView mTv_title;
        private final TextView mTv_author;
        private final TextView mTv_subTitle;
        private final TextView mTv_pubDate;
        private final TextView mTv_pages;
        private final TextView mTv_prices;
        private Book mBook;

        public HotBooksViewHolder(View itemView) {
            super(itemView);
            mIv_head = (ImageView) itemView.findViewById(R.id.book_cover);
            mTv_title = (TextView) itemView.findViewById(R.id.txt_title);
            mTv_author = (TextView) itemView.findViewById(R.id.txt_author);
            mTv_subTitle = (TextView) itemView.findViewById(R.id.txt_subTitle);
            mTv_pubDate = (TextView) itemView.findViewById(R.id.txt_pubDate);
            mTv_pages = (TextView) itemView.findViewById(R.id.txt_pages);
            mTv_prices = (TextView) itemView.findViewById(R.id.txt_prices);

            itemView.setOnClickListener(this);
        }
        public void updateBook(Book book) {
            if (book == null) return;
            mBook = book;
            Context context = itemView.getContext();

            Picasso.with(context).load(book.getImages().getLarge())
                    .placeholder(context.getResources().getDrawable(R.mipmap.ic_launcher))
                    .into(mIv_head);

            mTv_title.setText(book.getTitle());
            mTv_author.setText("作者："+book.getAuthor_intro());
            mTv_subTitle.setText("副标题："+book.getSubtitle());
            mTv_pubDate.setText("出版日期："+book.getPubdate());
            mTv_pages.setText("页数："+book.getPages());
            mTv_prices.setText("定价："+book.getPrice());
        }

        @Override
        public void onClick(View v) {
            LogUtils.e("点击item");
            if (mBook == null) return;
            if (itemView == null) return;
            Context context = itemView.getContext();
            if (context == null) return;
            Intent intent = new Intent(context, BookDetailActivity.class);
            intent.putExtra("book",mBook);
            if(context instanceof AppCompatActivity){
                AppCompatActivity activity= (AppCompatActivity) context;
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, mIv_head, "cover").toBundle();
                ActivityCompat.startActivity(activity, intent, bundle);
            }
        }
    }
}
