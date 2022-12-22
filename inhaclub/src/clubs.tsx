import React from 'react';
import InfoAnnyeong from "./img/img_info_annyeong.png";
import Annyeong from "./img/img_annyeong.png";
import { Link } from 'react-router-dom';

function Clubs() {
  return (
    <div id="wrap">
    <div className="uord_box">
        <Link to="/register">
            <button className="btn">수정</button>
        </Link>
    </div>
    <div className="info_box">
        <div className="tit01">안뇽 동아리 18기 모집</div>
        <div className="info_img"><img src={InfoAnnyeong} alt="안뇽"/></div>
        <div className="info_desc">
            <ul>
                <li>
                    <div className="tit02">동아리 설명</div>
                    <div className="txt">가장 오랜 역사를 간직한 안뇽 동아리는 인하대의 명물인 안뇽을 따라 인경호의 비밀을 파헤친다.</div>
                </li>
                <li>
                    <div className="tit02">인원</div>
                    <div className="txt">약 32명</div>
                </li>
                <li>
                    <div className="tit02">모집 여부</div>
                    <div className="txt">모집중 D - 2</div>
                </li>
                <li>
                    <div className="tit02">모집 기간</div>
                    <div className="txt">2022/07/07 ~ 2022/09/09</div>
                </li>
                <li>
                    <div className="tit02">관심 분야</div>
                    <div className="txt">언론/미디어</div>
                </li>
            </ul>
        </div>
    </div>

    <div className="txt_box">
        <div className="tit02">활동</div>
        <div className="txt">
            가장 오랜 역사를 간직한 안뇽 동아리는 인하대의 명물인 안뇽을 따라 인경호의 비밀을 파헤친다. 안뇽이가 귀엽고요ㅠ 인덕이를 만날 수 있어요. 좀 길게 쓰고 싶은데 뭘 써야 할 지 몰라서 복붙하겠습니다. 가장 오랜 역사를 간직한 안뇽 동아리는 인하대의 명물인  안뇽을 따라 인경호의 비밀을. 파헤친다. 인덕이가 귀여운 동아리 많관부 많관부 많관부 그냥 레퍼런스로 주신 거를 참고할게요.
        </div>
        <div className="txt">
            청소년과 청년이 함께 만들어 가는 어쩌구 4기 기자단을 모집합니다.\n
            작성한 기사는 대충 유명한 미디어그런곳에 송출됩니다. 전국 기자들과의 네트워크도 형성
        </div>
        <div className="txt">
            <ul>
                <li>1. 대한민국청소년 의회 사이트 기사 작성</li>
                <li>2. 24시간 내 송고 시스템 운영</li>
                <li>3. 언론인 멘토강연 참여</li>
            </ul>
        </div>
    </div>

    <div className="txt_box">
        <div className="tit02">담당자 정보</div>
        <div className="txt">
            <ul>
                <li>담당자 : 가나다</li>
                <li>연락처 : 010 1234 5678</li>
                <li>이메일 : hjskdhjkf@naver.com</li>
            </ul>
        </div>
    </div>

    <div className="img_box">
        <div className="tit01">동아리 사진</div>
        <div className="img_wrap">
            <img src={Annyeong} alt="안뇽"/>
        </div>
        <div className="img_wrap">
            <img src={Annyeong} alt="안뇽"/>
        </div>
        <div className="img_wrap">
            <img src={Annyeong} alt="안뇽"/>
        </div>
    </div>
    </div>
 );
}

export default Clubs;
