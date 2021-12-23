package formatter;

import model.Clazz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import service.IClazzService;


import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class ClazzFormatter implements Formatter<Clazz> {
    private final IClazzService clazzService;

    @Autowired
    public ClazzFormatter(IClazzService classService) {
        this.clazzService = classService;
    }

    @Override
    public Clazz parse(String text, Locale locale) throws ParseException {
        Optional<Clazz> classOptional = clazzService.findById(Long.parseLong(text));
        return classOptional.orElse(null);
    }

    @Override
    public String print(Clazz object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
