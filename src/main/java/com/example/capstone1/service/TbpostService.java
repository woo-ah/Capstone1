package com.example.capstone1.service;

import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.TbpostDto;

import java.util.List;

public interface TbpostService {
    TbpostDto.CreateResDto create(TbpostDto.CreateReqDto param);
    TbpostDto.CreateResDto update(TbpostDto.UpdateReqDto param);
    TbpostDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostDto.DetailResDto> list(TbpostDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbpostDto.PagedListReqDto param);
    List<TbpostDto.DetailResDto> scrollList(TbpostDto.ScrollListReqDto param);
}
