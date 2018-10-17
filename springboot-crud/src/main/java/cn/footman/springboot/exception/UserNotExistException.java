package cn.footman.springboot.exception;

/**
 * @author footman77
 * @create 2018-09-22 16:35
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("没有用户");
    }
}
