package com.ray.java.chapter1;

/**
 * 乘法表
 * @author LeiYong
 */
public class Multiplication {
    /**
     * 打印乘法表
     * 思路:首先必须非常明确的分析需求,需要打印99乘法表
     * 接下来思考如何实现?
     * 已知条件:
     * 1.第二个数不能大于第一个数
     * 2.每次打印完成后需换行
     * 3.每次需要对第一个数加1后迭代性的对第二个数加1
     * 实现:
     * 1.先用一个for循环用于控制第一个数每次加1
     * 2.迭代性的对第二个数加1,并且让第二个数需要小于第一个数
     *
     * @param count 打印总数
     */
    public static void multiplication(int count){
        for (int i = 1;i < count;i++){
            for (int j = 1;j <=i;){
                String result = j+"*"+i+"="+i*j;
                if (++j<=i){
                    result +="\t";//制表符,相当于tab键
                }else{
                    result += "\r\n";//换行
                }
                System.out.print(result);
            }
        }
    }

    /**
     * 使用递归实现打印乘法表,需对如上需求明确分析,对代码明确掌握,知道何时执行哪行代码
     * 建议尽可能的尝试自己实现该运算方式,(PS:容易造成死循环,不会调试的慎之)
     * 其实写出来发现然并卵,并不是什么复杂的东西
     * @param count
     * @param i
     * @param j
     */
    public static void iterationMultiplication(int count,int i,int j) {
        if(i==count){
            return;
        }
        String result = j+"*"+i+"="+i*j;
        if (j<i){
            j++;
            result +="\t";//制表符,相当于tab键
        }else{
            result += "\r\n";//换行
            i++;
            j=1;
        }
        System.out.print(result);
        iterationMultiplication(count,i,j);
    }
    public static void main(String[] args) {
        System.out.println("---------循环方式乘法表------------");
        multiplication(15);
        System.out.println("---------递归方式乘法表------------");
        iterationMultiplication(15,1,1);
    }
}
