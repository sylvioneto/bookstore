package br.com.spedroza.bookstore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

//cart class defined with session scope

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable{

    /**
	 * This class should implemetn Serializable because it is session scoped
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<CartItem, Integer> items = new LinkedHashMap<>();

    public Collection<CartItem> getItems() {
		return items.keySet();
	}

	public void add(CartItem item) {
		System.out.println("Inside Cart.add");
        items.put(item, getQuantity(item) + 1);
    }

    public Integer getQuantity(CartItem item) {
        if(!items.containsKey(item)) {
            items.put(item, 0);
        }
        return items.get(item);
    }

    public Integer getQuantity() {
        return items.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
    }
    
    // total by item
    public BigDecimal getTotal(CartItem item){
    	return item.getTotal(getQuantity(item));
    }
    
    // total in cart
    public BigDecimal getTotal(){
    	BigDecimal total = BigDecimal.ZERO;
    	for(CartItem item : items.keySet()) {
    		total = total.add(getTotal(item));
    	}
    	return total;
    }

	public void remove(Integer produtoId, PriceType priceType) {
		System.out.println("inside Cart.remover");
		Product product = new Product();
		product.setId(produtoId);
		items.remove(new CartItem(product, priceType));
	}
	
	public void clear() {
		items.clear();
	}
	
}