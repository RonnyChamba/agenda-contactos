console.log("Hola saludando desde js");
const $btnEliminar = [...document.querySelectorAll(".btn-eliminar-js")];

$btnEliminar.forEach( (item)=>{
		
	item.addEventListener("click", function(event) {	
  	event.preventDefault();
    
	let confirma =  confirm("Seguro desea eliminar el contacto:");
	
	console.log(this);
	
	if (confirma){
		
		event.defaultPrevented = true;		
	}
	
});

} );

