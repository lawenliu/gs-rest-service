package hello;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /**
     * Request with "/greeting/Body"
     * @param name parameter
     * @return Jackson JSON
     */
    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
    public Greeting greetingRestful(@PathVariable("name") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    /**
     * Request with "/greeting?name=Body"
     * @param name parameter
     * @return Jackson JSON
     */
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greetingNormal(@RequestParam(value="name", defaultValue="World", required = true) String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
