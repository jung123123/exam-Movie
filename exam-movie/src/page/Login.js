import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {  useEffect,useState,useRef } from 'react';
import '../css/login.css'

export default function Login(){

    const [logId, setLogId] = useState();       //아이디
    const [logPwd, setLogPwd] = useState();     //비밀번호
    const [loginResult, setLoginResult] = useState(null);       // 로그인 상태
    
    const navigate = useNavigate();

    const getLogin =()=>{   //  signup 테이블 select
        const url = `/api1/signup/${logId}/${logPwd}`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                if (response.data !== "") {
                    setLoginResult('로그인 성공');
                    navigate(`/`,{state: { loginId: logId }});

                } else {
                    setLoginResult('로그인 실패');
                }
            })
            .catch((error) => {
                console.error("에러:", error);
                setLoginResult('로그인 실패');    
            });
    };


    const txtid = (e) => {    //아이디 TEXT
        setLogId(e.target.value);
        console.log(logId);
    }

    const txtpwd = (e) => {    //비밀번호 TEXT
        setLogPwd(e.target.value);
        console.log(logPwd);
    }

    const btnlogin = (e) => {     //로그인 버튼 클릭시
        getLogin();
    }




    return(
        <div className="logintitle">

            <h2 className='loginname'>로그인</h2>
            
           <p>아이디: <input type='text' onChange={txtid}/></p>
           <p>비밀번호: <input type='password'onChange={txtpwd}/></p>
            <button className='loginbtn' onClick={btnlogin}>로그인</button>
            {loginResult && <p>{loginResult}</p>}
        </div>     
            
    )
}