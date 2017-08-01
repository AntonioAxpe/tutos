$(function(){
	
	$('.cart_table__product__quantity__buttons .btn_more, .cart_table__product__quantity__buttons .btn_less').on('click', function(){
		
		var index = +($(this).parent().parent().parent().index()) - 1;
		var unit = $('.cart_table__product__quantity:eq('+index+') span').html();
		var totalUnit;
		
		if ($(this).hasClass("btn_more")) {
			totalUnit = +unit + 1;
		}else if ($(this).hasClass("btn_less")) {
			totalUnit = +unit - 1;
		}
		
		$('.cart_table__product__quantity:eq('+index+') span').html(totalUnit);
	});

	
	
});