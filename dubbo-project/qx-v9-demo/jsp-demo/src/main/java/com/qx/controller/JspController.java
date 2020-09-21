package com.qx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Author: QX_He
 * DATA: 2020/9/9-15:26
 * Description:
 **/

@Controller
@CrossOrigin
public class JspController {

        @RequestMapping("/getjsp")
        public String getJspPage(){
            return "demo01";
        }

    public static void main(String[] args) {

        int a[]=new int[1];//初始化数组
        a[0]=1;//为第一个设置初始化值
        int n=8;
        for(int i=0;i<n;i++){//n次方
            for(int j=0;j<a.length;j++){//对数组里面的每一个值都乘上2
                a[j]*=2;
            }
            for(int j=0;j<a.length-1;j++){//判断是否大于等于10
                if(a[j]>=10){//如果是则前面一位进1，自己对10 取余数
                    a[j]%=10;
                    a[j+1]++;
                }
            }
            if(a[a.length-1]>=10){//最后一位大于等于10，则数组大小增加一位，并且最大位置置1
                a[a.length-1]%=10;
                int temp[]=new int[a.length+1];//临时数组，用来转化
                System.arraycopy(a, 0, temp, 0, a.length);
                a=temp;
                a[a.length-1]=1;
            }
        }
        System.out.println("输出结果是： ");
        for(int i=a.length-1;i>=0;i--){//倒序输出则是结果
            System.out.print(a[i]);
        }
        System.out.println();
    }
}
