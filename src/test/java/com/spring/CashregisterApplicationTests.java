package com.spring;

import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.persistence.entity.*;
import com.spring.persistence.springdata.*;
import com.spring.presentation.UserController;
import com.spring.service.*;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
public class CashregisterApplicationTests {
     
    @MockBean
    private UserImpl userImpl; 
    @MockBean
    private GoodsImpl goodsImpl;
    @MockBean
    private ChecImpl checkImpl;
	@Autowired
	UserService userService;	
	@Autowired
	GoodsService goodsService;
	@Autowired
	CheckService checkService;
	
	private Goods goods;
	
    @Before
    public void setUp() throws Exception {
    	goods = new Goods(1l, 4, "картошка", 40, "кг");
    }
    @Test
    public void testUser() {

    	User user = new User(1l, "goods_spec", "1", "Сергей");
    	user.setUserType(new UserType(1l, "goods_spec"));
    	user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
    	
    	when(userImpl.findByLoginAndPassword("a1@gmail.com", "1")).thenReturn(user);
    	assertEquals(userService.findByLoginAndPassword("a1@gmail.com", "1"), user);
    	
    	when(userImpl.findById(1l)).thenReturn(Optional.of(user));
    	assertEquals(userService.findById(1l), Optional.of(user));
    	
    	ArrayList<User> users = new ArrayList<User>();
    	users.add(user);
    	when(userImpl.findByUserType(user.getUserType())).thenReturn(users);
    	assertThat(users, notNullValue());
    	assertThat(users, hasSize(1));
    	assertThat(userService.findByUserType(user.getUserType()), is(users));
    	
    	when(userImpl.findByLogin("a1@gmail.com")).thenReturn(user);
    	assertEquals(userService.findByLogin("a1@gmail.com"), user);
    	HttpSession session = mock(HttpSession.class);
    	new UserController(userService).postSignIn("a1@gmail.com", "1", session, null);
    	verify(session, times(1)).setAttribute("user", user);
    }
    
    @Test
    public void testGoods() {    	
    	
    	when(goodsImpl.findByCode(4)).thenReturn(Optional.of(goods));
    	assertEquals(goodsService.findByCode(4), Optional.of(goods));
    	
    	when(goodsImpl.findById(1l)).thenReturn(Optional.of(goods));
    	when(goodsImpl.findByName("картошка")).thenReturn(Optional.of(goods));
    	assertEquals(goodsService.findById(1l), Optional.of(goods));
    	assertEquals(goodsService.findByName("картошка"), Optional.of(goods));
    }
    
    @Test
    public void testCheck() {
    	when(goodsImpl.findByCode(4)).thenReturn(Optional.of(goods));	//поиск по коду товара
    	goods.setPrice(12);
    	Checkspec spec = checkService.addCheckSpec(goods.getCode(), "", 1.5, 20);
    	assertEquals(spec.getGoods(), goods);
    	assertTrue(spec.getQuant() == 1.5);
    	assertTrue(spec.getTotal() == 18);
    	assertTrue(spec.getNds() == 20);
    	assertTrue(spec.getNdstotal() == BigDecimal.valueOf(18).multiply(new BigDecimal(0.2)).doubleValue());
    	
    	when(goodsImpl.findByName("картошка")).thenReturn(Optional.of(goods));	//поиск по наименованию товара
    	spec = checkService.addCheckSpec(null, "картошка", 1.5, 20);
    	assertEquals(spec.getGoods(), goods);
    }
}
