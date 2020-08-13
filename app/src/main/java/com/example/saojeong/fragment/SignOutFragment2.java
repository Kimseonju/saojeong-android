package com.example.saojeong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.saojeong.MainActivity;
import com.example.saojeong.R;

public class SignOutFragment2 extends Fragment {

    private Button btn_sign_out;
    private EditText et_sign_out;
    private String signOutReason;
    public static SignOutFragment2 newInstance() { return new SignOutFragment2(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_sign_out2, container, false);

        btn_sign_out = view.findViewById(R.id.btn_out2);
        et_sign_out = view.findViewById(R.id.et_sign_out);

        signOutReason = et_sign_out.getText().toString();

        btn_sign_out.setOnClickListener((v) -> {
            //todo 서버로 전송
            //todo 프로필로 이동
            ((MainActivity)getActivity()).replaceFragment(MyPageFragment.newInstance());
        });

        return view;
    }
}