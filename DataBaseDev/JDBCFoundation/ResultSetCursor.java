/*
    结果集光标与元数据
        DriverManager
            其实我们今后只需要会用DriverManager的getConnection()方法即可：
            1.Class.forName("com.mysql.cj.jdbc.Driver");    //注册成功
            2.String url = "jdbc:mysql://localhost:3306/mydb1";
            3.String username = "kwj-at-lzu";
            4.String password = "code";
            5.Connection con = DriverManager.getConnection(url, username, password);
            
            注意：上面的代码可能出现两种异常状况
            1.ClassNotFoundException:这个异常是在1句上出现的，出现此异常有两种可能：
                你没有导入MySQL的jar包
                你把类名称打错了，查看类名称是不是com.mysql.cj.jdbc.Driver
            2.SQLException:这个异常出现在5句，出现此异常是三个参数的问题
                往往username与password不会出错，所以要关注url是否打错

            对于DriverManager.registerDriver()方法了解即可，因为我们今后注册驱动只会使用Class.forName(),
                而不会使用此方法
        Connection
            Connection最为重要的方法就是获取Statement:
                Statement stmt = con.createStatement();
            后面在学习ResultSet方法时，还要学习一下下面的方法：
                Statement stmt = con.createStatement(int, int);
                                            //这两个参数是用来确定Statement能生成什么样的结果集
        Statement
            Statement最为重要的方法是：
                int executeUpdate(String sql):执行更新操作，即执行insert、update、delete语句，
                    其实这个方法也可以执行create table, alter table, 以及drop table等语句，
                    但我们很少使用JDBC来执行这些语句
                ResultSet executeQuery(String sql):执行查询操作，查询操作会返回ResultSet,即结果集

                boolean execute();  //了解即可
                    Statement还有一个boolean execute()方法，这个方法可以用来执行增、删、改、查所有SQL语句
                    该方法的返回值是boolean类型，表示SQL语句是否有结果集
                    如果使用execute()方法执行的是更新语句：
                        那么还要调用int getUpdateCount来获取insert、update、delete语句所影响的行数
                    如果使用execute()方法执行的是查询语句：
                        那么还要调用ResultSet getResultSet()来获取select语句的查询结果
                
        ResultSet之滚动结果集（了解） MySQL数据库默认就是滚动的，可以调用如下方法
            ResultSet表示结果集，它是一个二维的表格！ 
            ResultSet内部维护一个行光标（游标），ResultSet提供了一系列方法来移动游标：
                下一行：默认只能使用它，其他的方法存在，但不能使用！默认的结果集不可滚动！
                上一行、下N行、上N行、到N行
                void beforeFirst():把光标放到第一行的前面，这也是光标默认的位置 //虚拟位置
                void afterLast():把光标放到最后一行的后面   //虚拟位置
                boolean first():把光标放到第一行的位置上，返回值表示调控光标是否成功
                boolean last():把光标放到最后一行的位置上

                boolean ifBeforeFirst():当前光标是否在第一行前面
                boolean ifAfterLast():当前光标是否在最后一行的后面
                boolean isFirst():当前光标位置是否在第一行上
                boolean isLast():当前光标是否在最后一行上

                boolean previous():把光标向上挪一行
                boolean next():把光标向下挪一行
                boolean relative(int row):相对位移，当row为正数时，表示向下移动row行
                        为负数时，表示向上移动row行
                boolean absolute(int row):绝对位移，把光标移动到指定的行上
                int getRow():返回当前光标所有行

                上面方法分为两类，一类用来判断游标位置，另一类用来移动游标

            结果集特性：当使用Connection的createStatement时，已经确定了Statement生成的结果集是什么特性
                1.是否可滚动    不可滚动即只能调用next()方法
                2.是否敏感
                3.是否可更新
                
                con.createStatement();生成的结果集:不滚动、不敏感、不可更新
                    如果结果集是不可滚动的，那么只能使用next()方法来移动游标，而其他方法统统不能使用
                    结果集是否支持滚动，要从Connection类的createStatement()方法说起，
                        也就是说创建的Statement决定——使用Statement创建的ResultSet是否支持滚动

                Statement createStatement(int resultSetType, int resultSetConcurrency); //了解
                    resultSetType的可选值：
                        ResultSet.TYPE_FORWARD_ONLY:不可滚动结果集
                        ResultSet.TYPR_SCROLL_INSENSITIVE:滚动结果集，但结果集数据不会再跟随数据库而变化
                        ResultSet.TYPE_SCROLL_SENSITIVE:滚动结果集，但结果集数据不会再跟随数据库而变化      //没有数据库驱动支持

                        可以看出，如果想使用滚动结果集，我们应该选择TYPE_SCROLL_INSENSITIVE!
                            其实很少有数据库驱动会支持TYPE_SCROLL_SENSITIVE的特性！
                            通常我们也不需要查询到的结果集再受到数据库变化的影响
                
                    resultSetConcurrency的可选值：
                        CONCUR_READ_ONLY:结果集是只读的，不能通过修改结果集而反向印象数据库
                        CONCUR_UPDATABLE:结果集是可更新的，对结果集的更新可以反向影响数据库
                            通常更新结果集这一“高级特性”我们也不需要
                    获取滚动结果集的代码如下：
                        Connection con = ... 
                        Statement stmt = con.createStatement(Result.TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY);
                        String sql = ...//查询语句
                        ResultSet rs = stmt.executeQuery(sql);  //这个结果集是滚动的

            获取结果集元数据（元数据相关）
                得到元数据：rs.getMetaData(), 返回值为ResultSetMetaData
                获取结果集列数：int getColumnCount()
                获取指定列的列名：String getColumnName(int colIndex)

                int count = rs.getMetaData().getColumnCount();
                while(rs.next()) {  //遍历行
                    for (int i = 1; i <= count; i++) {  //遍历列
                        System.out.print(rs.getString(i));
                        if (i < count) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                }

        ResultSet之获取列数据
            可以通过next()方法使ResultSet的游标向下移动，当游标移动到你需要的行时，就需要来获取该行的数据了
            ResultSet提供了一系列的获取列数据的方法：
                String getString(int columnIndex):获取指定列的String类型数据
                int getString(int columnIndex):获取指定列的int类型数据
                double getDouble(int columnIndex):获取指定列的double类型数据
                boolean getBoolean(int columnIndex):获取指定列的boolean类型数据
                Object getObject(int columnIndex):获取指定列的Object类型数据
                上面的方法中，参数columnIndex表示列的索引，列索引从1开始，而不是0，这一点与数组不同
                    如果你清楚当前列的数据类型，那么就可以使用getInt()之类的方法来获取，
                    如果你不清楚列的类型，那么你应该使用getObject()方法来获取
            ResuleSet还提供了一套通过列名称来获取列数据的方法:
                String getString(String columnName):获取名称为columnName列的String数据
                int getInt(String columnName):获取名称为columnName列的int数据
                double getDouble(String columnName):获取名称为columnName的列的double数据
                boolean getBoolean(String columnName):获取名称为columnName的列的boolean数据
                Object getObject(String columnName):获取名称为columnName的Object数据
*/
public class ResultSetCursor {

}