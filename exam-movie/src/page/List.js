import axios from 'axios';
import { useNavigate,useLocation } from 'react-router-dom';
import {  useEffect,useState,useRef } from 'react';
import '../css/list.css'

export default function List(){

    const navigate = useNavigate();
    const location = useLocation();
    const { loginId } = location.state || {};   //로그인 체크
    const logId = "로그인";
  
    
    const [genrelist, setGenrelist] = useState([]);       //genre 화면  튜플배열?
    const [companylist, setCompanylist] = useState([]);   //company 화면  튜플배열?
    const [movielist, setMovielist] = useState([]);       //movie 화면  튜플배열?
    const [loading, setLoading] = useState(true);         //화면 로딩중 

    const [gname, setGname] = useState();     //genreName  text
    const [cname, setCname] = useState();   //companyName  text
    const [mnane, setMname] = useState();   //movieName  text
    const [mgnum, setMgnum] = useState();   //moviegenre  text
    const [mcnum, setMcnum] = useState();   //moviecompany  text
    const [mimg, setMimg] = useState();     //movieimg  text
    const [maddress, setMaddress] = useState();   //movieaddress text


    const genreNameRef = useRef();      //input 초기화
    const companyNameRef = useRef();    //input 초기화
    const movieNameRef = useRef();      //input 초기화
    const genreNumberRef = useRef();    //input 초기화
    const companyNumberRef = useRef();  //input 초기화
    const ImgRef = useRef();            //input 초기화
    const addressRef = useRef();        //input 초기화


    

    useEffect(() => {     
      navigate('/list', { state: { loginId: logId } });
      getGenre();   //genre 테이블 select
      getCompany(); //company 테이블 select
      getMovie();   //movie 테이블 select

    }, []);

    

    const getGenre =()=>{          //genre 테이블 select
        const url = `/api1/genre/getAll`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setGenrelist(response.data);
                setLoading(false);
            });
    };

    const getCompany =()=>{          //company 테이블 select
        const url = `/api1/company/getAll`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setCompanylist(response.data);
                setLoading(false);
            });
    };

    const getMovie =()=>{         //movie 테이블 select
        const url = `/api1/movie/getName`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setMovielist(response.data);
                setLoading(false);
            });
    };

    const eff = () => {
        getGenre(); //genre 테이블 select
      getCompany(); //company 테이블 select
      getMovie();   //movie 테이블 select
    }


    const GPosts = ()=>{      //genre 테이블 insert
        const url = `/api1/genre/${gname}`;

       axios.post(url)
      .then((response) => {
        console.log("응답객체:", response);
        setLoading(false);
        eff();
      });
    }

    const chgname= (e) => {        //genreName  text 
        setGname(e.target.value);
    }

    const genrePost = () => {   // 추가 버튼 
        GPosts();
        // input 값 초기화
        genreNameRef.current.value = '';  

    }

    const CPosts = ()=>{      //company 테이블 insert
        const url = `/api1/company/${cname}`;

       axios.post(url)
      .then((response) => {
        console.log("응답객체:", response);
        setLoading(false);
        eff();
      });
    }

    const chcname= (e) => {   //companyName  text 
        setCname(e.target.value);
    }

    const CompanyPost = () => {   //추가 버튼
        CPosts();
        // input 값 초기화
        companyNameRef.current.value = '';  

    }

    const MPosts = ()=>{        //movie 테이블 insert
        const url = `/api1/movie/name=${mnane}/genre=${mgnum}/company=${mcnum}`;

       axios.post(url)
      .then((response) => {
        console.log("응답객체:", response);
        setLoading(false);
        eff();
        setMname("");
        setMgnum("");
        setMcnum("");
        setMaddress("");
        setMimg("");
        })
        .catch((error) => {
        console.error(error);});
    }

    const MoPosts = ()=>{    
        const url = `/api1/movie`;
        const data = {
            movieName : mnane,
            genre : mgnum, 
            company : mcnum,
            movieImg : mimg
        }
       axios.post(url,data)
      .then((response) => {
        console.log("응답객체:", response);
        setLoading(false);
        eff();
        setMname("");
        setMgnum("");
        setMcnum("");
        setMaddress("");
        setMimg("");
      });
    }


    const chmname = (e) => {     //movieName  text
        setMname(e.target.value); 
    }

    const chmgnum = (e) => {        //moviegenre  text
        setMgnum(e.target.value);
    }

    const chmcnum = (e) => {        //moviecompany  text
        setMcnum(e.target.value);
    }

    const chmddress = (e) => {          //movieaddress  text
        setMaddress(e.target.value);
    }

    const chmimg = (e) => {       //movieimg  text
        const file = e.target.files[0];
        setMimg(file);
    }
    
    


    const MoviePost = () => {   //추가 버튼
        MPosts();

        movieNameRef.current.value = '';  
        genreNumberRef.current.value = '';  
        companyNumberRef.current.value = '';  
        // ImgRef.current.value = '';  
        // addressRef.current.value = '';  

    }

    const gdelete = (genreNumber)=>{      //genre 테이블 delete
        const url = `/api1/genre/${genreNumber}`;
        axios.delete(url)
        .then((response) => {
          console.log("응답객체:", response);
          setLoading(false);
          eff();
        })
        .catch((error) => {
          console.error("장르 삭제 실패:", error);
        });
    };


    const deleteg = (genreNumber) => {    //삭제버튼
        gdelete(genreNumber);
    }


    const cdelete = (companyNumber)=>{      //company 테이블 delete
        const url = `/api1/company/${companyNumber}`;
        axios.delete(url)
        .then((response) => {
          console.log("응답객체:", response);
          setLoading(false);
          eff();
        })
        .catch((error) => {
          console.error("제작사 삭제 실패:", error);
        });
    };


    const deletec = (companyNumber) => {     //삭제버튼
        cdelete(companyNumber);
    }

    const mdelete = (movieNumber)=>{      //movie 테이블 delete
        const url = `/api1/movie/${movieNumber}`;
        axios.delete(url)
        .then((response) => {
          console.log("응답객체:", response);
          setLoading(false);
          eff();
        })
        .catch((error) => {
          console.error("영화 삭제 실패:", error);
        });
    };


    const deletem = (movieNumber) => {     //삭제버튼
        mdelete(movieNumber);
    }

    const basics = () => {    //기본값 버튼
        deleteAll();
        PostGenre(genreData);   //genre 테이블 기본값 insert
        PostCompany(companyData);//company 테이블 기본값 insert
        PostMovie(movieyData);//movie 테이블 기본값 insert
    }

    const deleteAll = ()=>{    //테이블(genre,company,movie) 삭제 truncate
        const url = `/api1/movie/deleteall`;
        axios.delete(url)
        .then((response) => {
          console.log("응답객체:", response);
          setLoading(false);
          
        })
        .catch((error) => {
          console.error("삭제 실패:", error);
        });
    };

    const PostGenre = (genreData)=>{     //genre 테이블 기본값 insert
        console.log("-------SaveToSB---------");
        return new Promise((resolve, reject) => {       //    버그인지는 모르겠으나 const a = [{a},{b},{c}]; 로 할 시  반복 적으로 실해하면 0 = a, 1 = b, 2 = c가 아님 랜덤하게 저장 되어 이러한 방식을 사용 
            setTimeout(async () => {      // 버튼 클릭시 삭제후 생성되는 작업이라 삭제가 먼저 실행되도록 딜레이
              try {
                for (const data of genreData) {             
                  const { genreName } = data;
                  const url = `/api1/genre/${genreName}`;
        
                  const response = await axios.post(url);
                  console.log("응답객체:", response);
                  setLoading(false);
                }
                resolve(); // 모든 요청이 완료되면 resolve 호출
              } catch (error) {
                reject(error); // 오류 발생 시 reject 호출
              }
            }, 2000);
          });
    };

    const genreData = [     
        {
            genreName: '액션'
          },
          {
            genreName: '범죄'
          },
          {
            genreName: 'SF'
          },
          {
            genreName: '판타지'
          },
          {
            genreName: '슬랩스틱'
          },
          {
            genreName: '로맨스'
          },
          {
            genreName: '호러'
          },
          {
            genreName: '블랙 코미디'
          },
          {
            genreName: '스릴러'
          }
    ];

    const PostCompany = (companyData)=>{     //company 테이블 기본값 insert
        console.log("-------SaveToSB---------");
        return new Promise((resolve, reject) => {       //    버그인지는 모르겠으나 const a = [{a},{b},{c}]; 로 할 시  반복 적으로 실해하면 0 = a, 1 = b, 2 = c가 아님 랜덤하게 저장 되어 이러한 방식을 사용 
            setTimeout(async () => {      // 버튼 클릭시 삭제후 생성되는 작업이라 삭제가 먼저 실행되도록 딜레이
              try {
                for (const data of companyData) {             
                  const { companyName } = data;
                  const url = `/api1/company/${companyName}`;
        
                  const response = await axios.post(url);
                  console.log("응답객체:", response);
                  setLoading(false);
                }
                resolve(); // 모든 요청이 완료되면 resolve 호출
              } catch (error) {
                reject(error); // 오류 발생 시 reject 호출
              }
              
            }, 2000);
          });
    };

    const companyData = [     
        {
            companyName: '넷플릭스'
          },
          {
            companyName: '유니버설 스튜디오'
          },
          {
            companyName: '메가박스'
          },
          {
            companyName: 'Lionsgate Films'
          },
          {
            companyName: '빅펀치픽쳐스'
          },
          {
            companyName: '닌텐도'
          },
          {
            companyName: '디지니'
          },
          {
            companyName: '스카이댄스 미디어'
          },
          {
            companyName: '마블 스튜디오'
          }
    ];

    const PostMovie = (movieyData)=>{     //movie 테이블 기본값 insert
        console.log("-------SaveToSB---------");
        return new Promise((resolve, reject) => {       //    버그인지는 모르겠으나 const a = [{a},{b},{c}]; 로 할 시  반복 적으로 실해하면 0 = a, 1 = b, 2 = c가 아님 랜덤하게 저장 되어 이러한 방식을 사용 
            setTimeout(async () => {      // 버튼 클릭시 삭제후 생성되는 작업이라 삭제가 먼저 실행되도록 딜레이
              try {
                for (const data of movieyData) {             
                  const { movieName,genre,company } = data;
                  const url = `/api1/movie/name=${movieName}/genre=${genre}/company=${company}`;
        
                  const response = await axios.post(url);
                  console.log("응답객체:", response);
                  setLoading(false);
                }
                resolve(); // 모든 요청이 완료되면 resolve 호출
              } catch (error) {
                reject(error); // 오류 발생 시 reject 호출
              }
              eff();
            }, 4000);
          });
    };

    const movieyData = [     
        {
            movieName:'스파이더맨: 어크로스 더 유니버스',
            genre:1,
            company:9
         },
         {
            movieName:'범죄도시3',
            genre:2,
            company:5
         },
         {
            movieName:'헝거 게임: 모킹제이',
            genre:3,
            company:4
         },
         {
            movieName:'슈퍼 마리오 브라더스',
            genre:5,
            company:2
         },
         {
            movieName:'셜록: 유령신부',
            genre:2,
            company:3
         },
         {
            movieName:'탑건: 매버릭',
            genre:1,
            company:8
         },
         {
            movieName:'겨울왕국',
            genre:4,
            company:7
         }
         
    ];

    return(
        <div>
            <h1>리스트 목록</h1>
            <button onClick={basics}>기본값</button>
            <div className='gridlist'>
                
                <div>
                    
                     장르 : <input type="text" ref={genreNameRef} onChange={chgname} placeholder='필수'/>
                    <button onClick={genrePost}>추가</button>

                </div>

                <div>
                    
                     제작사 : <input type="text" ref={companyNameRef} onChange={chcname} placeholder='필수'/>
                    <button onClick={CompanyPost}>추가</button>

                </div>

                <div>
                    
                    제목 : <input type="text" ref={movieNameRef} onChange={chmname} placeholder='필수'/> <br/>
                    장르 : <input type="text" ref={genreNumberRef} onChange={chmgnum} placeholder='필수(번호)'/><br/>
                    제작사 : <input type="text" ref={companyNumberRef} onChange={chmcnum} placeholder='필수(번호)'/> <br/>
                  
                   
                    <button onClick={MoviePost}>추가</button>

               </div>



            </div>

            <hr/>
            <div className='gridlist'>

                <div className="scroll-container">
                    {loading ? (
                        <h1>Loading</h1>
                    ) : (
                        genrelist.map((p) => (
                        <div key={p.genreNumber} className="maintitle">
                            <p>번호: {p.genreNumber}</p>
                            <p>장르: {p.genreName}&nbsp;&nbsp;<button onClick={()=>{deleteg(p.genreNumber)}}>삭제</button></p>
                            
                            <hr/>
                        </div>
                        ))
                    )}
                </div>

                <div className="scroll-container">
                    {loading ? (
                        <h1>Loading</h1>
                    ) : (
                        companylist.map((p) => (
                        <div key={p.companyNumber} className="maintitle">
                            <p>번호: {p.companyNumber}&nbsp;&nbsp;</p>
                            <p>제작사: {p.companyName}&nbsp;&nbsp;<button onClick={()=>{deletec(p.companyNumber)}}>삭제</button></p>
                            <hr/>
                        </div>
                        ))
                    )}
                </div>

                <div className="scroll-container">
                    {loading ? (
                        <h1>Loading</h1>
                    ) : (
                        movielist.map((p) => (
                        <div key={p.movieNumber} className="maintitle">
                            <p>이름: {p.movieName}&nbsp;&nbsp;</p>
                            <p>장르: {p.genre}&nbsp;&nbsp;</p>
                            <p>제작사: {p.company}&nbsp;&nbsp;<button onClick={()=>{deletem(p.movieNumber)}}>삭제</button>
                            </p>
                            <hr/>
                        </div>
                        ))
                    )}
                </div>
            </div>


        </div>
    )
}