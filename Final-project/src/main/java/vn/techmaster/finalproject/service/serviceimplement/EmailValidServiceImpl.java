package vn.techmaster.finalproject.service.serviceimplement;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidServiceImpl implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}
