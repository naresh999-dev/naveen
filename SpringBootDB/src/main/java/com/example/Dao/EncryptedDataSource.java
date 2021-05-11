package com.example.Dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
@Configuration
public class EncryptedDataSource  {
	

final String keypass = "naresh!!!!";
private static SecretKeySpec secretKey;
private static byte[] key;
@Value("${spring.datasource.password}")
private String password;
@Value("${spring.datasource.username}")
private String userName;
@Value("${spring.datasource.url}")
private String url;
@Value("${spring.datasource.driverClassName}")
private String driverClass;
@Bean
public DataSource getDataSource() {
  
    return DataSourceBuilder.create()
            .username(userName)
            .password(decrypt(password, keypass))
            .url(url)
            .driverClassName(driverClass)
            .build();
}

/*
 * @Bean
 * 
 * @Primary public JpaProperties getHibernateProperties() { JpaProperties
 * jpaProperties=new JpaProperties(); jpaProperties.setShowSql(false); return
 * jpaProperties;
 * 
 * }
 */

/*
 * @Bean public LocalContainerEntityManagerFactoryBean
 * entityManagerFactory(DataSource dataSource) {
 * LocalContainerEntityManagerFactoryBean em = new
 * LocalContainerEntityManagerFactoryBean(); em.setDataSource(dataSource);
 * em.setPackagesToScan(new String[] { "com.example" });
 * 
 * JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
 * em.setJpaVendorAdapter(vendorAdapter);
 * 
 * Properties properties = new Properties();
 * properties.setProperty("spring.jpa.show-sql", "true");
 * 
 * em.setJpaProperties(properties);
 * 
 * return em; }
 */
 





 
	@PostConstruct
	public String encode() {
		String password="password";
		System.out.println(encrypt(password, keypass));
		return encrypt(password, keypass);
		
	}

	public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
	
	
}
