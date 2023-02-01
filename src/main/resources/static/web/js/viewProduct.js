const activeImage = document.querySelector(".product-image .active");
const productImages = document.querySelectorAll(".image-list img");
const navItem = document.querySelector('a.toggle-nav');

function changeImage(e) {
  activeImage.src = e.target.src;
}

function toggleNavigation(){
  this.nextElementSibling.classList.toggle('active');
}

productImages.forEach(image => image.addEventListener("click", changeImage));
navItem.addEventListener('click', toggleNavigation);




const {createApp} = Vue

const product = createApp({
    data(){
        return {
           login: true, 
           


            
        }
    },
    created(){
        
        
    },
    methods: {
     
            
    },
    computed: {

    }
})


product.mount('#product')