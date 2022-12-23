import React from "react";
import Logo from "./img/inha_logo.png";
import Biryong from "./img/biryong.png";
import { useEffect, useState } from "react";

type MyFormProps = {
    onSubmit: (form: {userId: string; userPw: string; userEmail: string}) => void
  };

function Join({onSubmit} : MyFormProps)
{
    const [form, setForm] = useState({
        userId : '',
        userPw : '',
        userEmail : '',
    });

    const onChange = (e : React.ChangeEvent<HTMLInputElement>) => {
        const {id, value} = e.target;
        setForm({
            ...form,
            [id] : value,
        });
    }

    const submit = () => {
    }

    return (
        <div className="outer mx-auto">
            <img className="biryong mt-5 col-lg-1 d-none d-sm-block" src={Biryong}></img>
            <div className="container mx-0 mt-5 col-lg-6 col-md-4">
                <img className="logo mb-2 col-lg-3 col-sm-5" src={Logo}></img>
                <input className="inputBox_1 my-2 col-lg-8" placeholder="ID : " value={userId} onChange={onChange}></input>
                <input className="inputBox_1 mb-2 col-lg-8" type="password" placeholder="Password : " value={userPw} onChange={onChange}></input>
                <input className="inputBox_1 col-lg-8" placeholder="동아리 이름 : "></input>
                <div className="mt-2">
                    <input className="inputBox_1 col-lg-6" placeholder="E-mail : " value={userEmail} onChange={onChange}></input>
                    <button className="inputBox_1 mx-2" style={{width:'15.4%', textAlign:'center'}}>인증하기</button>
                </div>
                <div className="mt-2">
                    <input className="inputBox_2 mb-2 col-lg-5" placeholder="인증번호 : "></input>
                    <button className="inputBox_2 mx-2" style={{width:'15.4%', textAlign:'center'}} onClick={submit}>확인</button>
                </div>
                <div className="joinText mt-3">회원가입 하기</div>
            </div>
            
        </div>
    );
}
export default Join;