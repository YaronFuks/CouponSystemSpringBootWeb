$('.item span').hide();

var all_spans = $('.item a').parent().find('span');

$('.item a').click(function(e){
    
    e.preventDefault();
    // hide all span
    all_spans.hide();
    $this = $(this).parent().find('span');
    // here is what I want to do
    if ($this.is(':hidden')) {
         $(this).parent().find('span').show();
    } else {
         $(this).parent().find('span').hide();
    }
    
});