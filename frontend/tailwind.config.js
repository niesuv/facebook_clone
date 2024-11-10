/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'selector',
  content: ["./src/**/*.{html,jsx,js}"],
  theme: {
    extend: {
      colors: {
        text: "#A7ABB0",
        blue: "#0766FF",
        black: "#242526",
        white: "#E6E9EC",
        primary: "#fff",
        gray: "#4E4F50",
        darkwhite: "#B9BBBE",
        pink: "#F23D57",
        yellow: "#F7B124",
        darkblack: "#18191A",
        third: "#e4e6eb",
        secondary: "#f0f2f5",
        lightblue: "#6AAFFD",
        green: '#42b72a',
        darkGreen: '#119F16'

      },
      textColor: {
        primary: "#050505",
      },
    },
  },

  plugins: [
  ],
};
