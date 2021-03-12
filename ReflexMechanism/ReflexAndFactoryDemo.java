/*
    反射与工厂设计模式：
        如果要想进行对象的实例化处理除了可以使用关键字new之外，还可以使用反射机制来完成，
            于是此时你一定会考虑一个问题：为什么要听过有一个反射的实例化？那么到底是使用关键字new还是使用反射呢？
        如果想要更好的理解这个问题，最好的解释方案就是通过工厂设计模式来解决
            工厂设计模式的最大特点是：
                客户端的程序类不直接牵扯到对象的实例化管理，只与接口发生关联，通过工厂类获取指定接口的实例化对象
        范例：简单进行接口对象实例化
            interface IMessage0312 {
                public void send(); //消息发送
            }
            class NetMessage0312 implements IMessage0312 {
                public void send() {
                    System.out.println("【网络消息发送】www.mldn.cn");
                }
            }
            public class ReflexAndFactoryDemo {
                public static void main(String [] args) throws Exception {
                    IMessage0312 msg = new NetMessage0312();    //如果直接实例化则一定会有耦合问题
                    //在实际的开发之中，接口的主要作用是为不同层提供有一个操作的标准
                    //但是如果此时直接将一个子类设置为接口实例化操作，那么一定会有耦合问题
                    //所以使用了工厂设计模式来解决此问题
                }
            }

            ---------------------------------------------------------------------
        范例：利用工厂设计模式解决耦合问题
            interface IMessage0312 {
                public void send(); // 消息发送
            }
            class NetMessage0312 implements IMessage0312 {
                public void send() {
                    System.out.println("【网络消息发送】www.mldn.cn");
                }
            }
            class Factory0312 {
                private Factory0312() {
                } // 没有产生实例化对象的意义，所以构造方法私有化

                public static IMessage0312 getInstance(String className) {
                    if ("netmessage0312".equalsIgnoreCase(className)) {
                        return new NetMessage0312();
                    }
                    return null;
                }
            }
            public class ReflexAndFactoryDemo {
                public static void main(String[] args) throws Exception {
                    IMessage0312 msg = Factory0312.getInstance("netmessage0312");
                    msg.send();
                }
            }

            此种工厂设计模式属于静态工厂设计模式，也就是说如果现在要追加一个子类，则意味着工厂类一定要作出修改
                因为不追加这种判断是无法获取指定接口对象的

        范例：为IMessage追加一个子类
            interface IMessage0312 {
                public void send(); // 消息发送
            }
            class NetMessage0312 implements IMessage0312 {
                public void send() {
                    System.out.println("【网络消息发送】www.mldn.cn");
                }
            }
            class CloudMessage implements IMessage0312 {
                public void send() {
                    System.out.println("【云消息】www.mldn.cn");
                }
            }
            class Factory0312 {
                private Factory0312() {
                } // 没有产生实例化对象的意义，所以构造方法私有化
                public static IMessage0312 getInstance(String className) {
                    if ("netmessage0312".equalsIgnoreCase(className)) {
                        return new NetMessage0312();
                    } else if ("cloudmessage".equalsIgnoreCase(className)) {
                        return new CloudMessage();
                    }
                    return null;
                }
            }
            public class ReflexAndFactoryDemo {
                public static void main(String[] args) throws Exception {
                    IMessage0312 msg = Factory0312.getInstance("cloudmessage");
                    msg.send();
                }
            }
            
            工厂设计模式最有效解决的是子类与客户端的耦合问题，但是解决的核心思想在于提供有一个工厂类作为过渡端
                可是随着项目的进行，你的IMessage接口可能会有更多的子类
                而且随着时间的推移，子类产生的可能越来越多
                那么此时就意味着，你的工厂类，永远都要进行修改，并且永无停止之日
            那么这时候，最好的解决方案就是，不使用关键字new来完成，因为关键字new在使用的时候需要有一个明确的类存在
                而newInstance()方法只需要有一个明确表示类名称的字符串即可应用
        范例：动态工厂设计模式
            interface IMessage0312 {
                public void send(); // 消息发送
            }
            class NetMessage0312 implements IMessage0312 {
                public void send() {
                    System.out.println("【网络消息发送】www.mldn.cn");
                }
            }
            class CloudMessage implements IMessage0312 {
                public void send() {
                    System.out.println("【云消息】www.mldn.cn");
                }
            }
            class Factory0312 {
                private Factory0312() {
                } // 没有产生实例化对象的意义，所以构造方法私有化
                public static IMessage0312 getInstance(String className) throws Exception {
                    IMessage0312 instance = null;
                    try {
                        instance = (IMessage0312) Class.forName(className).getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return instance;
                }
            }
            public class ReflexAndFactoryDemo {
                public static void main(String[] args) throws Exception {
                    IMessage0312 msg = Factory0312.getInstance("NetMessage0312"); // 要注意必须是所定义的类名，确定大小写
                    msg.send();
                }
            }

            这个时候可以发现，利用反射机制实现的工厂设计模式，最大的优势在于：对于接口子类的扩充，将不再影响到工厂类的定义

            但是现在依然需要进一步思考，因为在实际的项目开发过程之中有可能会存在有大量的接口
                并且这些接口都可能需要通过工厂类实例化，所以此时的工厂设计模式不应该只为一个IMessage接口服务，
                而应该为所有的接口服务
        范例：使用了泛型+反射的工厂设计模式
            interface IMessage0312 {
                public void send(); // 消息发送
            }
            interface IService0312 {
                public void service(); // 服务
            }
            class NetMessage0312 implements IMessage0312 {
                @Override
                public void send() {
                    System.out.println("【网络消息发送】www.mldn.cn");
                }
            }
            class HouseService implements IService0312 {
                @Override
                public void service() {
                    System.out.println("【服务】为您的住宿提供服务");
                }
            }
            class Factory0312 {
                private Factory0312() {
                } // 没有产生实例化对象的意义，所以构造方法私有化

                **
                * 获取接口实例化对象
                * 
                * @param className 接口的子类
                * @param clazz     描述的是一个接口的类型
                * @return 如果子类存在则返回指定接口实例化对象
                *
                @SuppressWarnings("unchecked")
                public static <T> T getInstance(String className, Class<T> clazz) throws Exception {
                    T instance = null;
                    try {
                        instance = (T) Class.forName(className).getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return instance;
                }
            }

            public class ReflexAndFactoryDemo {
                public static void main(String[] args) throws Exception {
                    IMessage0312 msg = Factory0312.getInstance("NetMessage0312", IMessage0312.class); // 要注意必须是所定义的类名，确定大小写
                    msg.send();
                    IService0312 service = Factory0312.getInstance("HouseService", IService0312.class);
                    service.service();
                }
            }
*/
interface IMessage0312 {
    public void send(); // 消息发送
}

interface IService0312 {
    public void service(); // 服务
}

class NetMessage0312 implements IMessage0312 {
    @Override
    public void send() {
        System.out.println("【网络消息发送】www.mldn.cn");
    }
}

class HouseService implements IService0312 {
    @Override
    public void service() {
        System.out.println("【服务】为您的住宿提供服务");
    }
}

class Factory0312 {
    private Factory0312() {
    } // 没有产生实例化对象的意义，所以构造方法私有化

    /**
     * 获取接口实例化对象
     * 
     * @param className 接口的子类
     * @param clazz     描述的是一个接口的类型
     * @return 如果子类存在则返回指定接口实例化对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String className, Class<T> clazz) throws Exception {
        T instance = null;
        try {
            instance = (T) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}

public class ReflexAndFactoryDemo {
    public static void main(String[] args) throws Exception {
        IMessage0312 msg = Factory0312.getInstance("NetMessage0312", IMessage0312.class); // 要注意必须是所定义的类名，确定大小写
        msg.send();
        IService0312 service = Factory0312.getInstance("HouseService", IService0312.class);
        service.service();
    }
}