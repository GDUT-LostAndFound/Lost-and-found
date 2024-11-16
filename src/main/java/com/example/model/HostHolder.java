package com.example.model;

/**
 * 传递用户信息
 */
public class HostHolder {
   static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

   public static Integer getUserId(){
   return threadLocal.get();
   }
   public static void setUserId(Integer userId){
   threadLocal.set(userId);
   }
   public static void clear(){
   threadLocal.remove();}
}
