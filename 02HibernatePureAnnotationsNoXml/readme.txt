1. Here we will create different class for SessionFactory, since SF is heavyWeight class & its instance is initialized only once, no need to initialize that class again and again.

2. Also, since it is initialized only once, we make sure to make it singleton & thread safe

3. Since it is pure annotation & java based hibernate config class, we need Properties file to read hibernate-JDBC configuration data in that properties file

4. class name is - HibernateSessionFactoryUtils

5. So everytime we need to create session object, we just need to class that class method, no need to create SF object again & again

