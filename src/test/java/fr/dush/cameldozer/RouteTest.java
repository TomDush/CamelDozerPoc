package fr.dush.cameldozer;

import java.util.Arrays;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.converter.dozer.DozerTypeConverterLoader;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.spring.javaconfig.test.JavaConfigContextLoader;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import fr.dush.cameldozer.domain.GeneratedContainer.Person;
import fr.dush.cameldozer.domain.PersonDTO;
import fr.dush.cameldozer.domain.PersonDomain;

@ContextConfiguration(locations = "fr.dush.cameldozer.RouteTest$ContextConfig", loader = JavaConfigContextLoader.class)
public class RouteTest extends AbstractJUnit4SpringContextTests {

    public static final String NAME = "Tony Stark";
    public static final int AGE = 35;
    public static final String EMPLOYER = "Avengers";
    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    @Test
    @DirtiesContext
    public void testSend_DTO_Object() throws Exception {

        resultEndpoint.expectedMessageCount(1);
        resultEndpoint.expectedBodiesReceived(new PersonDomain(NAME, AGE, EMPLOYER));

        // Send message
        PersonDTO ironman = new PersonDTO(NAME, AGE, EMPLOYER);
        template.sendBodyAndHeader(ironman, "foo", "bar");

        // Assert
        resultEndpoint.assertIsSatisfied();
    }

    @Test
    @DirtiesContext
    public void testSend_generated_object() throws Exception {

        resultEndpoint.expectedMessageCount(1);
        resultEndpoint.expectedBodiesReceived(new PersonDomain(NAME, AGE, EMPLOYER));

        // Send message
        Person ironman = new Person(NAME, AGE, EMPLOYER);
        template.sendBodyAndHeader(ironman, "foo", "bar");

        // Assert
        resultEndpoint.assertIsSatisfied();
    }

    @Configuration
    public static class ContextConfig extends SingleRouteCamelConfiguration {

        @Bean
        public RouteBuilder route() {
            return new RouteBuilder() {

                public void configure() {
                    from("direct:start").convertBodyTo(PersonDomain.class).to("mock:result");
                }
            };
        }

        @Bean
        public DozerBeanMapper createDozerMapper(CamelContext camelContext) {
            DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList(new String[] { "dozer/dto.mappers.xml",
                    "dozer/generated.mappers.xml" }));
            new DozerTypeConverterLoader(camelContext, mapper);

            return mapper;
        }
    }
}
