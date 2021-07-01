package com.example.apptemplate2021.dto;

import java.lang.reflect.Member;

public class MemberDTO {
    public final String TAG = "MemberDTO";
    private String mem_id = null;
    private String mem_pw = null;
    private String mem_email = null;
    private String mem_name = null;

    public MemberDTO(){

    }

    public static MemberDTO getInstance(){return LazyHolder.instance;}
    private static class LazyHolder {
        private static final MemberDTO instance = new MemberDTO();
    }
}
