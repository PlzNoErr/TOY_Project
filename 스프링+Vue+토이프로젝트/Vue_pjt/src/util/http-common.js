import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://localhost:9999",
  timeout: 1000,
  headers: {
    "access-token": sessionStorage.getItem("access-token"),
  },
});
