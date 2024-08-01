package com.example.capstone1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class DefaultDto {
    @Builder
    @Schema
    @Getter
    @Setter
    public static class FileResDto{
        private String url;
    }

    /**/

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto {
        @Schema(description = "id", example="")
        @NotNull
        @NotEmpty
        private String id;

        @Schema(description = "deleted", example="")
        private String deleted;
        @Schema(description = "process", example="")
        private String process;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailReqDto{
        @Schema(description = "id", example="")
        @NotNull
        @NotEmpty
        private String id;
    }
    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto{
        @Schema(description = "id", example="")
        private String id;
        @Schema(description = "deleted", example="")
        private String deleted;
        @Schema(description = "process", example="")
        private String process;
        @Schema(description = "createdAt", example="")
        private String createdAt;
        @Schema(description = "modifiedAt", example="")
        private String modifiedAt;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto{
        @Schema(description = "deleted", example="")
        private String deleted;
        @Schema(description = "process", example="")
        private String process;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto{

        @Schema(description = "callpage", example="") //호출할 페이지
        private Integer callpage;
        @Schema(description = "perpage", example="") //페이지당 항목 수
        private Integer perpage;
        @Schema(description = "orderby", example="") //정렬할 기준
        private String orderby;
        @Schema(description = "orderway", example="") //정렬 방향 (오름차순, 내림차순)
        private String orderway;

        //원래는 고객한테 받으면 안되는 정보!
        @Schema(description = "offset", example="") //데이터를 조회할 때 건너뛸 항목 수
        private Integer offset;

        @Schema(description = "deleted", example="")
        private String deleted;
        @Schema(description = "process", example="")
        private String process;


        public int[] init(int listsize){
            if(orderby == null || orderby.isEmpty()){
                orderby = "created_at";
            }
            if(orderway == null || orderway.isEmpty()){
                orderway = "desc"; //내림차순
            }
            if(perpage == null || perpage < 1){
               perpage = 10;  //한번에 조회할 글 갯수
            }
            if(callpage == null){
                callpage = 1; //호출하는 페이지
            }
            if(callpage < 1){
                callpage = 1;
            }

            int pagesize = listsize / perpage;
            if(listsize % perpage > 0){
                pagesize++;
            }
            if(callpage > pagesize){
                callpage = pagesize;
            }
            //offset은 데이터 조회 시 건너뛸 항목수
            offset = (callpage - 1) * perpage;
            int[] res = {listsize, pagesize};
            return res;
        }


        public PagedListResDto afterBuild(int[] resSize, Object list){
            return PagedListResDto.builder()
                    .callpage(callpage)
                    .perpage(perpage)
                    .orderby(orderby)
                    .orderway(orderway)
                    .listsize(resSize[0])
                    .pagesize(resSize[1])
                    .list(list)
                    .build();
        }
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListResDto{

        @Schema(description = "callpage", example="")
        private Integer callpage;
        @Schema(description = "perpage", example="")
        private Integer perpage;
        @Schema(description = "orderby", example="")
        private String orderby;
        @Schema(description = "orderway", example="")
        private String orderway;

        @Schema(description = "listsize", example="")
        private Integer listsize;
        @Schema(description = "pagesize", example="")
        private Integer pagesize;

        @Schema(description = "list", example="")
        private Object list;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto{

        @Schema(description = "cursor", example="")
        private String cursor;
        @Schema(description = "perpage", example="")
        private Integer perpage;
        @Schema(description = "orderby", example="")
        private String orderby;
        @Schema(description = "orderway", example="")
        private String orderway;

        @Schema(description = "deleted", example="")
        private String deleted;
        @Schema(description = "process", example="")
        private String process;

        public void init(){
            if(orderby == null || orderby.isEmpty()){
                orderby = "created_at";
            }
            if(orderway == null || orderway.isEmpty()) {
                orderway = "desc";
            }
            if(perpage == null || perpage < 1){
                //한번에 조회할 글 갯수
                perpage = 10;
            }
        }
    }
}
