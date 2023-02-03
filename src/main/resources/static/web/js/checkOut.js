const {createApp} = Vue

const products = createApp({
    data(){
        return {
          login: true, 
          products: [],
          productsFilter: [],
          searchInput: "",
          select: "Category",
          categories: [],
          productCart: [],
          firstNameInput : '',
          lastNameInput : '',
          emailInput: '',
          cityInput: '',
          stateInput : '',
          zipCodeInput: '',
          streetNameInput: '',
          streetNumberInput: '',
          aptNumber: '',
          agreeCheckbox: false,
          arrayCliente : []
        }
    },
    created(){
        //this.loadData()
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
              console.log(this.products);
            }).catch(error => console.error(error))
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
        clientInfo(){
            if(this.firstNameInput == '' || this.lastNameInput == '' || this.emailInput == '' || this.cityInput == '' || this.stateInput == '' || this.zipCodeInput == '' || this.streetNameInput == '' || this.streetNumberInput == '')
            {
                alert("Al required fields must be completed")
            }else{
                if(!this.agreeCheckbox){
                    alert("Agree terms and conditions to continue")
                }else{
                    this.arrayCliente.push(this.firstNameInput,this.lastNameInput,this.emailInput,this.cityInput,this.stateInput,this.zipCodeInput,this.streetNameInput,this.streetNumberInput,this.aptNumber)
                    localStorage.setItem("clientOrder", JSON.stringify(this.arrayCliente))
                    window.location = ("./cardForm/pay.html")
                }
            }
        }
            
    },
    computed: {
        search() {
            const filterCategory = this.products.filter(product => product.categories[0].includes(this.select) || this.select === "Category") 
            this.productsFilter = filterCategory.filter(product => product.name.toLowerCase().trim().includes(this.searchInput.toLowerCase().trim()))
        }
    }
})


products.mount('#products')