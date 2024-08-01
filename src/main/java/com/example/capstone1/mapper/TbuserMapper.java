package com.example.capstone1.mapper;

import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.TbuserDto;

import java.util.List;

public interface TbuserMapper {
    TbuserDto.DetailResDto login(TbuserDto.LoginReqDto param); //로그인 정보 확인

    TbuserDto.DetailResDto detail(DefaultDto.DetailReqDto param); //상세 정보 조회
    List<TbuserDto.DetailResDto> list(TbuserDto.ListReqDto param);//목록 조회

    List<TbuserDto.DetailResDto> scrollList(TbuserDto.ScrollListReqDto param);//스크롤 방식 목록 조회
    List<TbuserDto.DetailResDto> pagedList(TbuserDto.PagedListReqDto param);//페이징된 목록 조회
    int pagedListCount(TbuserDto.PagedListReqDto param);//페이징된 목록의 총 개수 조회
}
