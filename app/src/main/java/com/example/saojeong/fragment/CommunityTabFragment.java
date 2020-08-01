package com.example.saojeong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.saojeong.R;
import com.example.saojeong.adapter.CommunityAdapter_item;
import com.example.saojeong.model.CommunityValue;
import com.example.saojeong.model.Community_CommentValue;

import java.util.ArrayList;
import java.util.List;

public class CommunityTabFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{

    CommunityAdapter_item mAdapter;
    ArrayList<CommunityValue> mCommunityValue;
    TextView btnLeft;
    TextView btnRight;
    TextView tvBoard;
    public static SwipeRefreshLayout swipe;
    public static NestedScrollView scroll;

    RecyclerView mRecyclerViewCommunity;
    int board=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.viewpaper_community, container, false);
        mRecyclerViewCommunity = view.findViewById(R.id.community_board);
        mCommunityValue= new ArrayList<>();
        btnLeft=view.findViewById(R.id.tv_community_btn_Left);
        btnLeft.setOnClickListener(this);
        btnRight=view.findViewById(R.id.tv_community_btn_Right);
        btnRight.setOnClickListener(this);
        scroll=view.findViewById(R.id.scrollns_community);
        tvBoard=view.findViewById(R.id.tv_community_board);
        swipe=view.findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(this);
        tvBoard.setText(board+1+"");

        //test
        List<Community_CommentValue> CCList;
        CCList = new ArrayList<>();
        CCList.add(new Community_CommentValue("a","a","a",false));
        CCList.add(new Community_CommentValue("b","b","b",false));
        CCList.add(new Community_CommentValue("c","c","c",false));
        CCList.add(new Community_CommentValue("d","d","d",false));

        CommunityValue com=new CommunityValue("1","1","1","1","1",0,0);
        CommunityValue com1=new CommunityValue("1","1","1","1","1",0,0);
        com.SetComment(CCList);
        com1.SetComment(CCList);
        mCommunityValue.add(com1);
        mCommunityValue.add(new CommunityValue("8","1","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","2","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","3","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","4","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","5","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","6","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","7","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","8","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","9","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","11011","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","1","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","2","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","3","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","4","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","5","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","6","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","7","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","8","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","9","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","1110","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","1","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","2","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","3","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","4","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","5","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","6","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","7","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","8","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","9","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","110111","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","1","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","2","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","3","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","4","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","5","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","6","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","7","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","8","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","9","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","11111110","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","1","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","2","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","3","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","4","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","5","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","6","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","7","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","8","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","9","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","1111111110","2","2","2",0,0));
        mCommunityValue.add(com);


        if(board==0)
        {
            btnLeft.setVisibility(View.GONE);
        }
        else
            btnLeft.setVisibility(View.VISIBLE);

        if((board+1)*10>=mCommunityValue.size())
        {
            btnRight.setVisibility(View.GONE);
        }
        else
            btnRight.setVisibility(View.VISIBLE);
        mAdapter = new CommunityAdapter_item(mCommunityValue, 0);
        mRecyclerViewCommunity.setAdapter(mAdapter);
        mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return view;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch(id)
        {
            case R.id.tv_community_btn_Left:
                mAdapter.DownBoard();
                board--;
                tvBoard.setText(board+1+"");
                scroll.scrollTo(0,0);
                mRecyclerViewCommunity.setAdapter(mAdapter);
                mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
                if(board==0)
                {
                    btnLeft.setVisibility(View.GONE);
                }
                else
                    btnLeft.setVisibility(View.VISIBLE);

                if((board+1)*10>=mCommunityValue.size())
                {
                    btnRight.setVisibility(View.GONE);
                }
                else
                    btnRight.setVisibility(View.VISIBLE);

                break;
            case R.id.tv_community_btn_Right:

                mAdapter.UpBoard();
                board++;
                tvBoard.setText(board+1+"");
                scroll.scrollTo(0,0);
                mRecyclerViewCommunity.setAdapter(mAdapter);
                mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
                if(board==0)
                {
                    btnLeft.setVisibility(View.GONE);
                }
                else
                    btnLeft.setVisibility(View.VISIBLE);

                if((board+1)*10>=mCommunityValue.size())
                {
                    btnRight.setVisibility(View.GONE);
                }
                else
                    btnRight.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onRefresh() {
        mCommunityValue.clear();

        mCommunityValue.add(new CommunityValue("8","333333333331","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","2","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","3","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","4","2","2","2",0,0));
        mCommunityValue.add(new CommunityValue("8","5","2","2","2",0,0));
        mAdapter = new CommunityAdapter_item(mCommunityValue, 0);
        mRecyclerViewCommunity.setAdapter(mAdapter);
        mRecyclerViewCommunity.setLayoutManager(new LinearLayoutManager(getActivity()));
        board=0;
        swipe.setRefreshing(false);
        if(board==0)
        {
            btnLeft.setVisibility(View.GONE);
        }
        else
            btnLeft.setVisibility(View.VISIBLE);

        if((board+1)*10>=mCommunityValue.size())
        {
            btnRight.setVisibility(View.GONE);
        }
        else
            btnRight.setVisibility(View.VISIBLE);
    }
}
