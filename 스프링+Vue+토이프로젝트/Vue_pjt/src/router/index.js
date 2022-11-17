import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import VideoView from "@/views/VideoView.vue";
import VideoDetail from "@/components/videos/VideoDetail.vue";
import SingupView from "@/components/users/SingupView.vue";
import LoginView from "@/components/users/LoginView.vue";
import FollowView from "@/components/users/FollowView.vue";
import UserView from "@/views/UserView.vue";
import MyVideoView from "@/components/users/MyVideoView.vue";
import ReviewView from "@/views/ReviewView.vue";
import ReviewDetail from "@/components/review/ReviewDetail.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "HomeView",
    component: HomeView,
  },
  {
    path: "/user",
    name: "UserView",
    component: UserView,
    children: [
      {
        path: "login",
        name: "LoginView",
        component: LoginView,
      },
      {
        path: "signup",
        name: "SingupView",
        component: SingupView,
      },
      {
        path: "follow",
        name: "FollowView",
        component: FollowView,
      },
      {
        path: "videolist",
        name: "MyVideoView",
        component: MyVideoView,
      },
    ],
  },
  {
    path: "/video",
    component: VideoView,
    children: [
      {
        path: ":videoId",
        name: "VideoDetail",
        component: VideoDetail,
      },
    ],
  },
  {
    path: "/review",
    component: ReviewView,
    children: [
      {
        path: ":reviewId",
        name: "ReviewDetail",
        component: ReviewDetail,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
