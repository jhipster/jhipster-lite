<template src="./App.html"></template>

<script lang="ts" src="./App.component.ts"></script>
<style>
:root {
  box-sizing: border-box;
  --bg-color-slate: #f1f5f9;
  --bg-color-brown: #f8f1ea;
  --bg-color-jh: #ebf1f7;
  --primary-color: #3474ab;
  --primary-color-hover: #245178;
  --primary-color-focus: #4ba8f8;
  --text-color: #343333;
  --variant-color-green: #bbf1e3;
  --variant-color-purple: #cfbef0;
  --variant-color-pink: #ffe2fb;
  --variant-color-orange: #ffefcd;
  --default-border-color: #e2e8f0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: var(--text-color);
}
.jh-container {
  width: 100%;
  margin-left: auto;
  margin-right: auto;
  padding-left: 1rem;
  padding-right: 1rem;
  margin-bottom: 1rem;
}
.jh-logo {
  width: 100%;
  box-sizing: border-box;
  margin-block-start: 0.67rem;
}
button {
  align-items: flex-start;
  box-sizing: border-box;
}
a,
button,
input[type='button'],
input[type='checkbox'],
input[type='checkbox'] + label,
input[type='file'],
input[type='image'],
input[type='radio'],
input[type='radio'] + label,
input[type='reset'],
input[type='submit'],
select,
textarea {
  cursor: pointer;
}
[contenteditable]:not([contenteditable='false']),
[tabindex],
a,
button,
input,
input[type='checkbox'] + label:before,
input[type='radio'] + label:before,
select,
textarea {
  outline-color: var(--primary-color-focus);
  outline-offset: 1px;
  outline-width: 1px;
}
.jh-btn {
  padding: 0.5rem 1rem;
  color: #eee;
  font-size: 1rem;
  display: inline-flex;
  flex-direction: row;
  line-height: 1.5rem;
  min-height: 2.5rem;
  z-index: 1;
}
/*------------------ Buttons group --------------------------*/
.jh-btns-group {
  list-style-type: none;
  display: flex;
  align-items: stretch;
  flex-wrap: wrap;
  margin-right: 0;
  margin-left: 0;
}
.jh-btns-group.jh-btns-group--inline > li {
  display: inline-flex;
  max-width: 100%;
  width: auto;
  padding-right: 1rem;
}
.jh-btns-group.jh-btns-group--inline .jh-btn {
  width: 100%;
}
.jh-btns-group--inline {
  width: calc(100% + 1rem);
}
.jh-btns-group .jh-btn {
  width: 100%;
  margin-right: 0;
  margin-left: 0;
  margin-bottom: 1rem;
  justify-content: center;
}
.jh-btns-group > li {
  width: 100%;
  max-width: 100%;
  padding: 0.5rem;
}
.btn-primary {
  background-color: var(--primary-color);
  border: 0;
}
.btn-primary:hover {
  background-color: var(--primary-color-hover);
}
.btn-primary:focus {
  outline-color: var(--primary-color-focus);
  outline-offset: 2px;
  outline-width: 2px;
}
ul,
ol {
  margin: 0;
  padding: 0;
}
label {
  display: block;
  font-size: 1rem;
  line-height: 1.5rem;
}
.jh-label + .jh-input,
.jh-label + .jh-input-wrap,
.jh-label + .jh-select {
  margin-top: 0.5rem;
}
.jh-input:not(textarea) {
  max-height: 2.5rem;
}
.jh-input {
  display: block;
  width: 100%;
  border-radius: 0.25rem 0.25rem 0 0;
  font-size: 1rem;
  line-height: 1.5rem;
  padding: 0.5rem 1rem;
  background-color: var(--bg-color-slate);
  box-shadow: inset 0 -2px 0 0 var(--primary-color);
  border: 0;
  box-sizing: border-box;
}
.jh-input:focus {
  outline-color: var(--primary-color-focus);
  outline-offset: 2px;
  outline-width: 2px;
}
input[type='checkbox'],
input[type='radio'] {
  /* Double-sized Checkboxes */
  -ms-transform: scale(2); /* IE */
  -moz-transform: scale(2); /* FF */
  -webkit-transform: scale(2); /* Safari and Chrome */
  -o-transform: scale(2); /* Opera */
  transform: scale(2);
}

/*------------------ Checkbox group --------------------------*/
.jh-checkbox-group {
  position: relative;
}
.jh-checkbox-group input[type='checkbox'] {
  position: absolute;
  top: 40%;
  z-index: 1;
  margin-left: 1rem;
}
.jh-checkbox-rich input[type='checkbox'] + label {
  margin-top: 0.5rem;
  margin-bottom: 0rem;
}
.jh-checkbox-group input[type='checkbox'] + label {
  padding-left: 3.5rem;
  margin-left: 0;
  min-height: 4.5rem;
  width: 100%;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  padding-right: 6.5rem;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  box-shadow: inset 0 0 0 1px var(--default-border-color);
  background-color: white;
}
.jh-checkbox-rich {
  position: relative;
}
.jh-checkbox-rich-img {
  padding-left: 0.25rem;
  width: 4.25rem;
  height: 97%;
  position: absolute;
  top: 0.1rem;
  right: 0.1rem;
  display: flex;
  flex-direction: row;
  pointer-events: none;
  background-color: var(--bg-color-slate);
}
.jh-checkbox-rich input[type='checkbox'] + label::before {
  width: 1rem;
  height: 1rem;
  position: absolute;
  top: 50%;
  left: 1.75rem;
  margin-top: -0.5rem;
  box-shadow: inset 0 0 0 1px var(--border-action-high-grey), inset 0 0 0 8px var(--bg-color-slate),
    inset 0 0 0 8px var(--background-action-high-blue-france);
}
.jh-checkbox-group input[type='checkbox'] + label::before {
  content: '';
  display: inline-block;
  position: absolute;
  top: 0;
  left: -2rem;
  width: 1.5rem;
  height: 1.5rem;
  margin-top: 0.75rem;
  margin-right: 0.5rem;
  border-radius: 50%;
  box-shadow: inset 0 0 0 1px var(--border-action-high-grey), inset 0 0 0 12px var(--bg-color-slate),
    inset 0 0 0 12px var(--background-action-high-blue-france);
}
.jh-checkbox-rich input[type='checkbox']:checked + label {
  box-shadow: inset 0 0 0 1px var(--primary-color);
}
.jh-checkbox-rich-img img,
.jh-checkbox-rich-img svg {
  max-width: 4rem;
  object-fit: contain;
}
*,
*::before,
*::after {
  box-sizing: inherit;
}
/*------------------ Dropdown --------------------------*/
.dropbtn {
  display: block;
}
.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown:hover .dropdown-content {
  display: block;
}

/*------------------*/
.title-highlight {
  padding-left: 1.5rem;
  line-height: 1.5rem;
  box-shadow: inset 0.25rem 0 0 0 var(--primary-color);
}
h1 {
  font-size: 4rem;
}
h1.brand {
  font-size: 3rem;
  line-height: 0;
}
.subtitle {
  font-size: 1.4rem;
  font-weight: lighter;
  text-transform: uppercase;
}
/*------------------ Card --------------------------*/
.card {
  position: relative;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-direction: column;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-color: #fff;
  background-clip: border-box;
  border: 1px solid rgba(0, 0, 0, 0.125);
}
.card-header {
  padding: 0.75rem 1.25rem;
  margin-bottom: 0;
  background-color: rgba(0, 0, 0, 0.03);
  border-bottom: 1px solid rgba(0, 0, 0, 0.125);
}
.card-header > img {
  height: 32px;
}
.card-header.spring {
  box-shadow: inset 0 -2px 0 0 #6bb536;
}
.card-body {
  -ms-flex: 1 1 auto;
  flex: 1 1 auto;
  padding: 1.25rem;
}
h2.card-header,
.card-header > h2 {
  margin-block-start: 0em;
}
.jh-container.card {
  padding-left: 0;
  padding-right: 0;
}
.card .jh-btn {
  margin-bottom: 0;
}
/*------------------ Spacing --------------------------*/
.p-1 {
  padding: 1rem;
}
.p-2 {
  padding: 2rem;
}
.py-1 {
  padding: 1rem 0;
}
.px-1 {
  padding: 0 1rem;
}
.px-0-5 {
  padding: 0 0.5rem;
}
.pr-1 {
  padding-right: 1rem;
}
.mx-0-5 {
  margin: 0 0.5rem;
}
.mb-1 {
  margin-bottom: 1rem;
}
/*------------------ Grid --------------------------*/
.row {
  display: flex;
  flex-direction: row;
  width: 100%;
}
.col-6 {
  width: 50%;
  padding: 0 0.5rem;
}
.col-1 {
  width: 8.33333%;
}
.col-3 {
  width: 25%;
  padding: 0 0.5rem;
}
.col-4 {
  width: 33.33333333%;
  padding: 0 0.5rem;
}
.col-9 {
  width: 75%;
}
.v-align-center {
  align-items: center;
}
.v-align-start {
  align-items: start;
}
.v-align-base {
  align-items: baseline;
}
/*------------------ Background variant --------------------------*/

.bg-variant-green {
  background-color: var(--variant-color-green);
}
.bg-variant-pink {
  background-color: var(--variant-color-pink);
}
.bg-variant-brown {
  background-color: var(--bg-color-brown);
}
.bg-variant-jh {
  background-color: var(--bg-color-jh);
}
.bg-variant-slate {
  background-color: var(--bg-color-slate);
}
/*------------------ @media --------------------------*/
@media (min-width: 78em) {
  .jh-container {
    max-width: 78rem;
  }
}

@media (min-width: 62em) {
  .jh-container {
    padding-left: 1.5rem;
    padding-right: 1.5rem;
  }
}
</style>
