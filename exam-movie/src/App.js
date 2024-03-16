import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import { useNavigate,useLocation  } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState, useRef } from 'react';
import Login from './page/Login';
import Join from './page/Join';
import './css/app.css';
import Main from './page/Main';
import List from './page/List';

function App() {
  const location = useLocation();
  const { loginId } = location.state || {};   //로그인 체크

  const shouldRenderMain = location.pathname === '/';
  
  const navigate = useNavigate();

  useEffect(() => {    //로그인 체크
    navigate('/', { state: { loginId: loginId } });
    console.log("loginId==========="+loginId);
  }, []);

  return (
    <div>
      <div className="title">
      <Link to={{ pathname: '/', state: { loginId: loginId } }} onClick={() => navigate('/', { state: { loginId: loginId } })}>홈</Link>&nbsp;|&nbsp;
        {loginId ? (
          <>
            <Link to={{ state: { loginId: loginId } }}>로그아웃</Link>&nbsp;|&nbsp;
            <Link to='/list'>리스트</Link>
          </>
        ) : (
          <>
            <Link to="/login">로그인</Link>&nbsp;|&nbsp;
            <Link to="/join">회원가입</Link>
          </>
        )}
        {shouldRenderMain && <Main />}

      </div>

      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/join" element={<Join />} />
        <Route path="/" />
        <Route path="/main" element={<Main />} />
        <Route path="/list" element={<List />} />
      </Routes>

      <div className="appbottom">
        <h1>1812037 박정현</h1>
        <h6>과제용</h6>
      </div>
    </div>
  );
}

export default function Root() {
  return (
    <BrowserRouter>
      <App />
    </BrowserRouter>
  );
}
