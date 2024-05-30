/* !! Axios 호출하기 !!

Axios는 Promise 기반의 HTTP 클라이언트로 브라우저와 Node.js에서 모두 사용할 수 있다.
Axios는 HTTP 요청을 실행하는 메서드를 제공한다.
Axios는 브라우저의 XMLHttpRequest 또는 Node.js의 http를 사용하여 HTTP 요청을 실행한다.
Axios는 요청과 응답 데이터를 JSON으로 자동 변환한다.
Axios는 요청을 취소할 수 있다.
Axios는 CSRF 보호를 위한 토큰을 설정할 수 있다.
Axios는 HTTP 요청과 응답을 인터셉트할 수 있다.
Axios는 Promise를 반환한다.
Axios는 요청을 취소할 수 있다.
Axios는 요청과 응답을 JSON으로 자동 변환한다.
Axios는 브라우저와 Node.js에서 사용할 수 있다.
Axios는 요청을 취소할 수 있다.
Axios는 요청을 인터셉트할 수 있다.
*/

async function get1(bno) {
    const result = await axios.get(`/api/replies/list/${bno}`);
    console.log(result);
    return result.data;
}

async function getList({bno, page, size, qoLast}) {
    const result = await axios.get(`/api/replies/list/${bno}?page=${page}`, {params: {page, size}});
    return result.data;
}