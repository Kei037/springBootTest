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

// 댓글 목록 가져오기
async function getList({bno, page, size, goLast}) {
    // axios.get(url, {params: {page, size}}) : url에 page와 size를 파라미터로 넘겨준다.
    const result = await axios.get(`/api/replies/list/${bno}?page=${page}`, {params: {page, size}});

    // goLast가 true일 경우 마지막 페이지로 이동
    if (goLast) {
        // result.data.total : 전체 댓글 수
        const total = result.data.total
        // Math.ceil(total / size) : 전체 페이지 수
        const lastPage = parseInt(Math.ceil(total / size))

        // getList({bno:bno, page:lastPage, size:size}) : 마지막 페이지로 이동
        return getList({bno:bno, page:lastPage, size:size})
    }

    return result.data;
}

// 댓글 추가하기
async function addReply(replyObj) {
    const response = await axios.post(`/api/replies/`, replyObj);
    return response;
}

// 댓글 조회
async function getReply(rno) {
    const response = await axios.get(`/api/replies/${rno}`);
    return response.data;
}

// 댓글 수정
async function modifyReply(replyObj) {
    const response = await axios.put(`/api/replies/${replyObj.rno}`, replyObj);
    return response.data;
}