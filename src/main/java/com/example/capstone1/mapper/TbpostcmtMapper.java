package com.example.capstone1.mapper;

import com.example.capstone1.dto.DefaultDto;
import com.example.capstone1.dto.TbpostcmtDto;

import java.util.List;

public interface TbpostcmtMapper {
    TbpostcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostcmtDto.DetailResDto> list(TbpostcmtDto.ListReqDto param);

    List<TbpostcmtDto.DetailResDto> scrollList(TbpostcmtDto.ScrollListReqDto param);
    List<TbpostcmtDto.DetailResDto> pagedList(TbpostcmtDto.PagedListReqDto param);
    int pagedListCount(TbpostcmtDto.PagedListReqDto param);
}
