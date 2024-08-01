package com.example.capstone1.service;

import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.TbpostfileDto;

import java.util.List;

public interface TbpostfileService {

    /**/
    TbpostfileDto.CreateResDto create(TbpostfileDto.CreateReqDto param);
    TbpostfileDto.CreateResDto update(TbpostfileDto.UpdateReqDto param);
    TbpostfileDto.CreateResDto delete(TbpostfileDto.UpdateReqDto param);
    TbpostfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostfileDto.DetailResDto> list(TbpostfileDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbpostfileDto.PagedListReqDto param);
    List<TbpostfileDto.DetailResDto> scrollList(TbpostfileDto.ScrollListReqDto param);
}
