package com.example.saojeong.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saojeong.R;
import com.example.saojeong.auth.TokenCase;
import com.example.saojeong.model.Community_CommentValue;
import com.example.saojeong.model.Post_CommentValue;
import com.example.saojeong.rest.ServiceGenerator;
import com.example.saojeong.rest.dto.board.CreateComentDto;
import com.example.saojeong.rest.dto.board.CreatePostDto;
import com.example.saojeong.rest.service.BoardService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class CommunityAdapter_Comment extends RecyclerView.Adapter<CommunityAdapter_Comment.ViewHolder>{

    int comment_id;
    int dodocument_id;
    public RelativeLayout mLayout;
    public Context mContext;
    CommunityAdapter_Comment mAdapter;
    public boolean replies;
    private BoardService boardService;
    public static String LOG="Comment";
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView_ID;
        public TextView mTextView_Date;
        public TextView mTextView_Content;
        public TextView mTextView_Btn_ReComment;
        public ImageView mImageView_Image;
        public LinearLayout mCommentLayout;
        public EditText mEditView_Recomment;
        public TextView mTextView_Btn_ReComment_Write;
        public RecyclerView mRecycleview;
//
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView_ID = (TextView) itemView.findViewById(R.id.tv_community_comment_id);
            mTextView_Date = (TextView) itemView.findViewById(R.id.tv_community_comment_date);
            mTextView_Content = (TextView) itemView.findViewById(R.id.tv_community_comment_contents);
            mTextView_Btn_ReComment = (TextView) itemView.findViewById(R.id.tv_community_comment_btn_recomment);
            mImageView_Image=(ImageView)itemView.findViewById(R.id.iv_community_comment_image);
            mEditView_Recomment=itemView.findViewById(R.id.et_community_comment_recomment);
            mTextView_Btn_ReComment_Write=itemView.findViewById(R.id.tv_community_btn_comment_write);
            mLayout=itemView.findViewById(R.id.item_community_layout);
            mCommentLayout=itemView.findViewById(R.id.ll_community_recomment);
            mCommentLayout.setVisibility(View.GONE);
            SpannableString content = new SpannableString(mTextView_Btn_ReComment.getText());
            content.setSpan(new UnderlineSpan(), 0, mTextView_Btn_ReComment.getText().length(), 0);
            mTextView_Btn_ReComment.setText(content);

            mTextView_Btn_ReComment.setOnClickListener(v -> {
                if(mCommentLayout.getVisibility()==View.VISIBLE) {
                    mCommentLayout.setVisibility(View.GONE);
                }
                else {
                    mCommentLayout.setVisibility(View.VISIBLE);
                    mEditView_Recomment.requestFocus();
                    InputMethodManager  inputMananger = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMananger.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
                //답글쓰기버튼클릭시 작동
            });

            mTextView_Btn_ReComment_Write.setOnClickListener(v -> {
                String str=mEditView_Recomment.getText().toString(); //
                if(str.length()>0){
                    createComment(str);
                    mEditView_Recomment.setText("");
                    notify();
                }

            });
            mRecycleview=itemView.findViewById(R.id.commentRecycle);
            if(replies)
                mRecycleview.setVisibility(View.GONE);
        }
    }

    private List<Post_CommentValue> mContacts;

    public CommunityAdapter_Comment(List<Post_CommentValue> contacts, Context context, int document_id, int comment_id,boolean replies) {
        this.replies=replies;
        mContext=context;
        mContacts = contacts;
        this.dodocument_id=document_id;
        this.comment_id=comment_id;
        boardService = ServiceGenerator.createService(BoardService.class, TokenCase.getToken());
    }
    @Override
    public CommunityAdapter_Comment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_community_comment, parent, false);

        CommunityAdapter_Comment.ViewHolder viewHolder = new CommunityAdapter_Comment.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommunityAdapter_Comment.ViewHolder holder, int position) {
        Post_CommentValue contact = mContacts.get(position);
        holder.mTextView_ID.setText(contact.getAuthor());
        holder.mTextView_Date.setText(contact.getCreatedAt());
        holder.mTextView_Content.setText(contact.getContent());
        //holder.mTextView_Btn_ReComment.setText("["+contact.GetComment().size() + "]");
        CheckReComment(contact.isReContent(), holder);
        mAdapter = new CommunityAdapter_Comment(contact.getReplies(), mContext,dodocument_id,-1, true);
        holder.mRecycleview.setAdapter(mAdapter);
        holder.mRecycleview.setLayoutManager(new LinearLayoutManager(mContext));
        holder.mRecycleview.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }



    public void CheckReComment(boolean check, CommunityAdapter_Comment.ViewHolder holder) {
        if(check) {
            holder.mTextView_Btn_ReComment.setVisibility(View.GONE);
            holder.mImageView_Image.setVisibility(View.VISIBLE);
        }
        else {
            holder.mTextView_Btn_ReComment.setVisibility(View.VISIBLE);
            holder.mImageView_Image.setVisibility(View.GONE);
        }
    }

    public void createComment(String contents) {
        boardService.createComment(new CreateComentDto(contents), 10004, 23).enqueue(new Callback<CreateComentDto>() {
            @Override
            public void onResponse(Call<CreateComentDto> call, Response<CreateComentDto> response) {

                if (response.code() == 201) {
                    if (response.code() == 201) {
                        CreateComentDto body = response.body();
                        Log.d(LOG, "전송완료");
                        notifyDataSetChanged();
                    }
                    Log.d(LOG, response.message());
                } else { // 서버에서 문제 발생
                    //likeStores = ContactShopOC._createContactsList(20);
                    //likeStoreAdapter = new LikeStoreAdapter(likeStores);
                }
            }

            @Override
            public void onFailure(Call<CreateComentDto> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });
    }

}

