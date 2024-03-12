package com.ohgiraffers.section01.xmlconfig;


/* 필기.
*   Service 의 역할
*   1. SqlSession 생성
*   2. DAO(데이터 접근 객체)의 메소드 호출
*   3. 트랙잭션(commit,rollback) 제어
*   4. SqlSession 닫기
*  */

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section01.xmlconfig.Template.getsqlSession;

public class MenuService {

    private final MenuDAO menuDAO;
    public MenuService(){
        this.menuDAO = new MenuDAO();
    }
    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getsqlSession();
        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);

        sqlSession.close();

        return menuList;

    }

    public MenuDTO selectMenuByCode(int code) {

        SqlSession sqlSession = getsqlSession();
        MenuDTO menu = menuDAO.selectMenuByCode(sqlSession, code);
        sqlSession.close();
        return menu;

    }


    public boolean registMenu(MenuDTO menu) {
        SqlSession sqlSession = getsqlSession();
        int result = menuDAO.insertMenu(sqlSession,menu);

        /* 트랜젝션 제어 */
        if(result > 0 ){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();

        return result > 0 ? true : false ;

    }

    public boolean updateMenu(MenuDTO menu) {

        SqlSession sqlSession = getsqlSession();
        int result = menuDAO.updateMenu(sqlSession,menu);
        if(result > 0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();

        return result > 0 ? true : false ;
    }

    public boolean deleteMenu(int code) {
        SqlSession sqlSession = getsqlSession();
        int result = menuDAO.deleteMenu(sqlSession,code);
        if(result > 0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();

        return result > 0 ? true : false ;
    }
}
