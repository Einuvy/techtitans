const {createApp} = Vue
const pay = createApp({
    el: "#pay",
    directives : {
        'mask': VueMask.VueMaskDirective
      },
    data() {
      return {
        currentCardBackground: Math.floor(Math.random() * 25 + 1),
        cardName: "",
        cardNumber: "",
        cardNumberFormatted: '',
        cardMonth: "",
        cardYear: "",
        cardCvv: "",
        minCardYear: new Date().getFullYear(),
        amexCardMask: "#### ###### #####",
        otherCardMask: "################",
        cardNumberTemp: "",
        isCardFlipped: false,
        focusElementStyle: null,
        isInputFocused: false,
        clientOrderStorage: '',
        clientOrderAmountStorage: ''
      };
    },
    mounted() {
      this.cardNumberTemp = this.otherCardMask;
      document.getElementById("cardNumber").focus();
    },
    computed: {
      getCardType() {  
        return "visa"; // default type
      },
      generateCardNumberMask() {
        return this.otherCardMask;
      },
      minCardMonth() {
        if (this.cardYear === this.minCardYear) return new Date().getMonth() + 1;
        return 1;
      }
    },
    watch: {
      cardYear() {
        if (this.cardMonth < this.minCardMonth) {
          this.cardMonth = "";
        }
      }
    },
    methods: {
      flipCard(status) {
        this.isCardFlipped = status;
      },
      focusInput(e) {
        this.isInputFocused = true;
        let targetRef = e.target.dataset.ref;
        let target = this.$refs[targetRef];
        this.focusElementStyle = {
          width: `${target.offsetWidth}px`,
          height: `${target.offsetHeight}px`,
          transform: `translateX(${target.offsetLeft}px) translateY(${target.offsetTop}px)`
        };
      },
      blurInput() {
        let vm = this;
        setTimeout(() => {
          if (!vm.isInputFocused) {
            vm.focusElementStyle = null;
          }
        }, 300);
        vm.isInputFocused = false;
      },
      formatNumber() {
        this.cardNumberFormatted = this.cardNumber.replace(/\s/g, '').replace(/(\d{4})/g, '$1 ').trim();
      },
      confirmPay(){
          this.formatNumber()
          this.clientOrderStorage = localStorage.getItem('clientOrder')
          this.clientOrderAmountStorage = localStorage.getItem('clientOrderAmount')
          console.log(this.clientOrderStorage)
            if(this.cardNumber.number == ''){
                alert("Card number empty")
            }else{
                axios.post('https://homebankingmbb.up.railway.app/api/pay',{
                    "amount" : this.clientOrderAmountStorage,
                    "cvv" : this.cardCvv,
                    "cardNumber" : "3493 8196 4687 1802",
                    "description" : this.clientOrderStorage
                })
               .then( () => {               
                localStorage.clear()  
                setTimeout(() => { window.location = ("/web/products.html") }, 1500) })
                .catch(function (error) {
                    if (error.response) {
                      alert("Something go wrong" + error.response.data);
                      console.log(error.response.data);
                      console.log(error.response.status);
                      console.log(error.response.headers);
                    } else if (error.request) {
                      console.log(error.request);
                    } else {
                      console.log('Error', error.message);
                    }
                  });
            }
    },
    }
  });
  
pay.mount('#pay')