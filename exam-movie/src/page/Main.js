import {BrowserRouter, Routes, Route, Link, useLocation} from "react-router-dom";
import axios from 'axios';
import {  useEffect,useState,useRef } from 'react';
import '../css/main.css'


export default function Main(){

    const [movielist, setMovielist] = useState([]);// movie 화면 튜플배열?
    const [loading, setLoading] = useState(true);//로딩중 화면
    const [moviename, setMoviename] = useState();//movieName text


    const getMovie =()=>{                           //영화 select    
        const url = `/api1/movie/getName`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setMovielist(response.data);
                setLoading(false);
            });
    };
     
    useEffect(getMovie,[]);


    const getFindMovie =()=>{           //검색한 단어로 영화 찾기
        const url = `/api1/movie/${moviename}`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setMovielist(response.data);
                setLoading(false);
            });
    };

    const findname =(e) =>{
        setMoviename(e.target.value);
    }

    const find =() =>{
        getFindMovie();
    }




    return(
        <div>

            <div className="maintitle">
            <input type="text" onChange={findname} placeholder='필수'/>
            <button onClick={find}>검색</button>
            </div>
            <hr/>
        
            <div className="gridmain">
                {loading ? (
                    <h1>Loading</h1>
                ) : (
                    movielist.map((p) => (
                    <div key={p.movieNumber} className="maintitle">
                     
                        <p>이름: {p.movieName}&nbsp;&nbsp;</p>
                        <p>장르: {p.genre}&nbsp;&nbsp;</p>
                        <p>제작사: {p.company}&nbsp;&nbsp;</p>
                    </div>
                    ))
                )}
            </div>

        </div>
    )
}