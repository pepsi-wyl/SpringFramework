package diy;
package diy;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author by wyl
 * @date 2021/9/26.
 */

/**
 * 执行顺序   环绕前  前置  方法体  afterReturning/afterThrowing  after  环绕后
 */

@Component             //托管到Spring中
@Scope("singleton")    //Bean作用域

@Aspect                //标注此类是切面类

@Order(1)              //@Order 设置该类对方法增强的优先级,数字越小优先级越高
public class diy {

    /**
     * @Pointcut (execution表达式) [公共切点]
     * 抽取公共的切入点
     */
    @Pointcut("execution(* dao.UserDaoImpl.*(..))")
    public void pointcutPublic() {
    }


    /**
     * 前置通知
     */
    @Before("pointcutPublic()")          //引用公共切入点
    public void before() {
        System.out.println("before!");
    }

    /**
     * 异常通知
     */
    @AfterThrowing("execution(* dao.UserDaoImpl.*(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing!");
    }

    /**
     * 后置通知（返回通知）  异常出现不执行
     */
    @AfterReturning("execution(* dao.UserDaoImpl.*(..))")
    public void afterReturning() {
        System.out.println("afterReturning!");
    }

    /**
     * 最终通知      异常出现也执行
     */
    @After("execution(* dao.UserDaoImpl.*(..))")
    public void after() {
        System.out.println("after!");
    }

    /**
     * 环绕通知
     */
    @Around("execution(* dao.UserDaoImpl.*(..))")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("执行前");
        point.proceed();    //相当于过滤
        System.out.println("执行后");
    }

}
