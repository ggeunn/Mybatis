package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.Template.getSqlSession;

public class MenuService {
    private DynamicSqlMapper mapper;
    public void selectMEnuByPrice(int price) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        /* 필기. 기본 자료형으로는 조건문의 값을 비교할 수 없으며 hashmap의 key
        *       혹은 dto 의 getter 를 이용하여 값을 확인 할 수 있다.
        *  */

        Map<String,Integer> map = new HashMap<>();
        map.put("price",price);

        List<MenuDTO> menuList = mapper.selectMenuByPrice(map);

        if(menuList != null && menuList.size() > 0){
            for(MenuDTO menu : menuList){
                System.out.println(menu);
            }
        }else {
            System.out.println("검색 결과가 없습니다.");
        }
        sqlSession.close();

    }

    public void searchMenu(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenu(searchCriteria);

        if(menuList != null && menuList.size() > 0){
            for (MenuDTO menu : menuList){
                System.out.println(menu);
            }
        }else {
            System.out.println("검색 결과가 없습니다. ");
        }
        sqlSession.close();
    }
}

