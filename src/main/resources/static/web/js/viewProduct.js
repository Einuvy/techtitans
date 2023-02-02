




const {createApp} = Vue

const product = createApp({
    data(){
        return {
           id: new URLSearchParams(location.search).get("id"),
           products: [],
           productId: [],
           activeImage: "",
          productImages: [],
          productCart: [],
          productsCategorie: []
            
        }
    },
    created(){
      this.loadData()
      if (localStorage.getItem("cart")) {
        this.productCart = JSON.parse(localStorage.getItem('cart'))
    }
    },
    methods: {
      loadData() {
        axios.get("/api/products")
        .then(res => {
          this.products = res.data
          this.productsFilter = this.products
          this.productId = this.products.find(product => product.id == this.id)
          this.activeImage = this.productId.image[0]
          this.productImages = this.productId.image
          this.productsCategorie = this.products.filter(product => product.categories[0] == this.productId.categories[0] ).slice(0, 3)
          console.log(this.products);
        }).catch(error => console.error(error))
      },
      changeImage(image) {
        this.activeImage = image;
      },
      addCart(product) {
        let alreadyInCart = this.productCart.find((item) => item.id === product.id)
        if (alreadyInCart) {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Product already in cart"
        })
        } else {
            this.productCart.push(product);
            this.saveCartToLocalStorage()
        }

    },
    removeFromCart(product) {
      const productIndex = this.productCart.findIndex(p => p.id === product.id);
      if (productIndex === -1) return
      this.productCart.splice(productIndex, 1)
      this.saveCartToLocalStorage()
    },
    saveCartToLocalStorage() {
      localStorage.setItem("cart", JSON.stringify(this.productCart))
    },
      
            
    },
    computed: {

    }
})


product.mount('#product')