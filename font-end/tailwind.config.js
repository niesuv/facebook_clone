/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,jsx,js}"],
  theme: {
    extend: {
      colors: {
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
      },
      textColor: {
        primary: "#050505",
      },
    },
  },

  plugins: [],
};
