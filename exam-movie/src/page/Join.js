import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {  useEffect,useState,useRef } from 'react';
import '../css/join.css'


export default function Join(){

    const [joinId, setJoinId] = useState(); //id text
    const [joinPwd, setJoinPwd] = useState();   //pwd text
    const [joinEmail, setJoinEmail] = useState();       //email text  
    const [loginResult, setLoginResult] = useState(null);   //중복 체크
    const [isDuplicate, setIsDuplicate] = useState(false);  //버튼 활성화 비활성화    

    const navigate = useNavigate();
    
    const Postjoin = ()=>{    // 회원가입 signup 테이블 insert
        const url = `/api1/signup/${joinId}/${joinPwd}/${joinEmail}`;

       axios.post(url)
      .then((response) => {
        console.log("응답객체:", response);
        navigate(`/`);
      });
    }

    const getjoin =(inputId)=>{   // signup 테이블 id select        아이디 중복 체크
        const url = `/api1/signup/${inputId}`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                if (response.data !== "") {
                    setLoginResult('중복 있음');
                    setIsDuplicate(false);

                } else {
                    setLoginResult('중복 없음');
                    setIsDuplicate(true);
                }
            })
            .catch((error) => {
                console.error("에러:", error);
                setLoginResult('중복 없음');   
                setIsDuplicate(true); 
            });
    };



    const txtid = (e) => {    //id text
        setJoinId(e.target.value);
        console.log(joinId);
        const inputId = e.target.value;
        getjoin(inputId);
    }

    const txtpwd = (e) => {     //pwd text
        setJoinPwd(e.target.value);
        console.log(joinPwd);
    }

    const txtemail = (e) => {   //email text  
        setJoinEmail(e.target.value);
        console.log(joinEmail);
    }


    const btnjoin = (e) => {   //가입하기 버튼
        if (!isDuplicate) {
            return; // 중복 확인이 완료되지 않았으면 가입 버튼을 비활성화
          } 
        Postjoin();
    }

  

    return(
        <div className="jointitle">
            

            <h2 className='joinname'>회원 가입</h2>
            
           <p>아이디: <input type='text' onChange={txtid}/>
            <div>
                {loginResult && <span>{loginResult}</span>}
            </div>
            </p>
            <p>비밀번호: <input type='password'onChange={txtpwd}/></p>
           <p>이메일: <input type='text'onChange={txtemail}/></p><br/>
            <button className='joinbtn' onClick={btnjoin} disabled={!isDuplicate}>가입하기</button>
            
              
        </div>
    )
}