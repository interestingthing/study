package cjg1.annotatedUtils;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 熟悉spring注解处理工具类
 */
public class AnnotationTest {


    /**
     * 获取语​​义（Get semantics）仅限于搜索AnnotatedElement上存在的注解（即本地声明或继承）或在AnnotatedElement上方的注解层次结构中声明的注解。
     * 查找语义（Find semantics）更加详尽，提供了语义加上对以下内容的支持：
     * <p>
     * 如果带注解的元素是类，则在接口上搜索
     * 如果带注解的元素是类，则在超类上搜索
     * 解析桥接方法，如果带注解的元素是方法
     * 如果带注解的元素是方法，则在接口中搜索方法
     * 如果带注解的元素是方法，则在超类中搜索方法
     * <p>
     * <p>
     * get语义之后的方法将遵循Java的@Inherited批注的约定，除了本地声明的注解（包括自定义组合注解）将优于继承注解。相反，查找语义之后的方法将完全忽略@Inherited的存在，因为查找搜索算法手动遍历类型和方法层次结构，从而隐式支持注解继承而不需要@Inherited。
     *
     * <span class="dev__title">　　父类拥有注解@RequestMapping，子类没有注解</span>
     * <span class="dev__title">　　父类没有注解，子类拥有注解@RequestMapping</span>
     * <span class="dev__title">　　父类拥有注解@PostMapping，子类没有注解</span>
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("ParentController getAnnotation @RequestMapping: " + AnnotationUtils.getAnnotation(ParentController.class, RequestMapping.class));
        System.out.println("ChildController getAnnotation @RequestMapping: " + AnnotationUtils.getAnnotation(ChildController.class, RequestMapping.class));
        System.out.println();

        System.out.println("ParentController findAnnotation @RequestMapping: " + AnnotationUtils.findAnnotation(ParentController.class, RequestMapping.class));
        System.out.println("ParentController findAnnotation @RequestMapping: " + AnnotationUtils.findAnnotation(ChildController.class, RequestMapping.class));
        System.out.println();

        System.out.println("ParentController isAnnotated @RequestMapping: " + AnnotatedElementUtils.isAnnotated(ParentController.class, RequestMapping.class));
        System.out.println("ParentController getMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.getMergedAnnotation(ParentController.class, RequestMapping.class));
        System.out.println("ChildController isAnnotated @RequestMapping: " + AnnotatedElementUtils.isAnnotated(ChildController.class, RequestMapping.class));
        System.out.println("ChildController getMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.getMergedAnnotation(ChildController.class, RequestMapping.class));
        System.out.println();

        System.out.println("ParentController hasAnnotation @RequestMapping: " + AnnotatedElementUtils.hasAnnotation(ParentController.class, RequestMapping.class));
        System.out.println("ParentController findMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.findMergedAnnotation(ParentController.class, RequestMapping.class));
        System.out.println("ChildController hasAnnotation @RequestMapping: " + AnnotatedElementUtils.hasAnnotation(ChildController.class, RequestMapping.class));
        System.out.println("ChildController findMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.findMergedAnnotation(ChildController.class, RequestMapping.class));
    }

    public static void main1(String[] args) {
        System.out.println("ParentController getAnnotation @RequestMapping: " + AnnotationUtils.getAnnotation(ParentController.class, RequestMapping.class));
        System.out.println("ChildController getAnnotation @RequestMapping: " + AnnotationUtils.getAnnotation(ChildController.class, RequestMapping.class));
        System.out.println("ParentController getAnnotation @PostMapping: " + AnnotationUtils.getAnnotation(ParentController.class, PostMapping.class));
        System.out.println("ChildController getAnnotation @PostMapping: " + AnnotationUtils.getAnnotation(ChildController.class, PostMapping.class));
        System.out.println();

        System.out.println("ParentController findAnnotation @RequestMapping: " + AnnotationUtils.findAnnotation(ParentController.class, RequestMapping.class));
        System.out.println("ParentController findAnnotation @RequestMapping: " + AnnotationUtils.findAnnotation(ChildController.class, RequestMapping.class));
        System.out.println("ParentController findAnnotation @PostMapping: " + AnnotationUtils.findAnnotation(ParentController.class, PostMapping.class));
        System.out.println("ParentController findAnnotation @PostMapping: " + AnnotationUtils.findAnnotation(ChildController.class, PostMapping.class));
        System.out.println();

        System.out.println("ParentController isAnnotated @RequestMapping: " + AnnotatedElementUtils.isAnnotated(ParentController.class, RequestMapping.class));
        System.out.println("ParentController getMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.getMergedAnnotation(ParentController.class, RequestMapping.class));
        System.out.println("ParentController isAnnotated @PostMapping: " + AnnotatedElementUtils.isAnnotated(ParentController.class, PostMapping.class));
        System.out.println("ParentController getMergedAnnotation @PostMapping: " + AnnotatedElementUtils.getMergedAnnotation(ParentController.class, PostMapping.class));
        System.out.println("ChildController isAnnotated @RequestMapping: " + AnnotatedElementUtils.isAnnotated(ChildController.class, RequestMapping.class));
        System.out.println("ChildController getMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.getMergedAnnotation(ChildController.class, RequestMapping.class));
        System.out.println("ChildController isAnnotated @PostMapping: " + AnnotatedElementUtils.isAnnotated(ChildController.class, PostMapping.class));
        System.out.println("ChildController getMergedAnnotation @PostMapping: " + AnnotatedElementUtils.getMergedAnnotation(ChildController.class, PostMapping.class));
        System.out.println();

        System.out.println("ParentController hasAnnotation @RequestMapping: " + AnnotatedElementUtils.hasAnnotation(ParentController.class, RequestMapping.class));
        System.out.println("ParentController findMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.findMergedAnnotation(ParentController.class, RequestMapping.class));
        System.out.println("ParentController hasAnnotation @PostMapping: " + AnnotatedElementUtils.hasAnnotation(ParentController.class, PostMapping.class));
        System.out.println("ParentController findMergedAnnotation @PostMapping: " + AnnotatedElementUtils.findMergedAnnotation(ParentController.class, PostMapping.class));
        System.out.println("ChildController hasAnnotation @RequestMapping: " + AnnotatedElementUtils.hasAnnotation(ChildController.class, RequestMapping.class));
        System.out.println("ChildController findMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.findMergedAnnotation(ChildController.class, RequestMapping.class));
        System.out.println("ChildController hasAnnotation @PostMapping: " + AnnotatedElementUtils.hasAnnotation(ChildController.class, PostMapping.class));
        System.out.println("ChildController findMergedAnnotation @PostMapping: " + AnnotatedElementUtils.findMergedAnnotation(ChildController.class, PostMapping.class));
    }
}

@RequestMapping(value = "parent/controller")
class ParentController {
}

class ChildController extends ParentController {
}
