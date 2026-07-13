---
name: Luminous Task System
colors:
  surface: '#f8f9ff'
  surface-dim: '#cbdbf5'
  surface-bright: '#f8f9ff'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#eff4ff'
  surface-container: '#e5eeff'
  surface-container-high: '#dce9ff'
  surface-container-highest: '#d3e4fe'
  on-surface: '#0b1c30'
  on-surface-variant: '#424754'
  inverse-surface: '#213145'
  inverse-on-surface: '#eaf1ff'
  outline: '#727785'
  outline-variant: '#c2c6d6'
  surface-tint: '#005ac2'
  primary: '#0058be'
  on-primary: '#ffffff'
  primary-container: '#2170e4'
  on-primary-container: '#fefcff'
  inverse-primary: '#adc6ff'
  secondary: '#006c49'
  on-secondary: '#ffffff'
  secondary-container: '#6cf8bb'
  on-secondary-container: '#00714d'
  tertiary: '#825100'
  on-tertiary: '#ffffff'
  tertiary-container: '#a36700'
  on-tertiary-container: '#fffbff'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#d8e2ff'
  primary-fixed-dim: '#adc6ff'
  on-primary-fixed: '#001a42'
  on-primary-fixed-variant: '#004395'
  secondary-fixed: '#6ffbbe'
  secondary-fixed-dim: '#4edea3'
  on-secondary-fixed: '#002113'
  on-secondary-fixed-variant: '#005236'
  tertiary-fixed: '#ffddb8'
  tertiary-fixed-dim: '#ffb95f'
  on-tertiary-fixed: '#2a1700'
  on-tertiary-fixed-variant: '#653e00'
  background: '#f8f9ff'
  on-background: '#0b1c30'
  surface-variant: '#d3e4fe'
typography:
  display-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 48px
    fontWeight: '700'
    lineHeight: '1.1'
    letterSpacing: -0.02em
  headline-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 32px
    fontWeight: '600'
    lineHeight: '1.2'
  headline-lg-mobile:
    fontFamily: Plus Jakarta Sans
    fontSize: 24px
    fontWeight: '600'
    lineHeight: '1.2'
  headline-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 24px
    fontWeight: '600'
    lineHeight: '1.3'
  headline-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 18px
    fontWeight: '600'
    lineHeight: '1.4'
  body-lg:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '400'
    lineHeight: '1.6'
  body-md:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: '1.5'
  body-sm:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: '1.5'
  label-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '500'
    lineHeight: '1'
    letterSpacing: 0.01em
  label-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '600'
    lineHeight: '1'
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  base: 4px
  xs: 4px
  sm: 8px
  md: 16px
  lg: 24px
  xl: 40px
  container-max: 1280px
  gutter: 24px
  margin-mobile: 16px
---

## Brand & Style
The brand personality is encouraging, organized, and frictionless. It aims to reduce the cognitive load of a student's busy schedule by providing a digital environment that feels like a clean desk. The target audience is students who need a reliable, non-intimidating tool to manage deadlines and study sessions.

This design system utilizes a **Modern Minimalist** style. It leans heavily on whitespace to create a sense of calm, using subtle color accents to guide attention rather than overwhelm. The aesthetic is "soft-functional"—relying on clear alignment and gentle transitions to make the interface feel responsive and approachable.

## Colors
The palette is rooted in a "Fresh Academic" theme. 
- **Primary (Sky Blue):** Used for main actions, active states, and focus indicators. It evokes clarity and focus.
- **Secondary (Emerald Green):** Reserved for completion states, success messages, and "deep work" indicators.
- **Tertiary (Amber):** Used sparingly for deadlines, high-priority warnings, or "due soon" alerts.
- **Neutral (Slate/Gray):** A sophisticated range of grays provides the structural framework, ensuring the UI feels grounded and professional.

Backgrounds should remain off-white (`#F8FAFC`) to reduce eye strain compared to pure white, while surfaces like cards and modals should be pure white (`#FFFFFF`) to pop forward.

## Typography
The system uses a pairing of **Plus Jakarta Sans** for headings and **Inter** for body text. Plus Jakarta Sans provides a friendly, contemporary geometric feel that makes the app feel "smart" yet accessible. Inter is used for all functional and reading text due to its exceptional legibility at small sizes and neutral character.

Maintain a vertical rhythm by using a 4px baseline. Large headlines should use tighter letter-spacing to appear more cohesive, while labels use slightly increased spacing for better scanability.

## Layout & Spacing
This design system uses a **Fluid Grid** model with a focus on generous internal padding to create "breathing room."
- **Desktop:** 12-column grid, 24px gutters, and a max-width container of 1280px.
- **Tablet:** 8-column grid, 24px gutters.
- **Mobile:** 4-column grid, 16px gutters, and 16px side margins.

Spacing follows a strict 8pt grid (4px, 8px, 16px, 24px, 32px, 40px, 64px). Use `lg` (24px) for padding within task cards and `xl` (40px) for section vertical spacing to maintain the minimal, airy aesthetic.

## Elevation & Depth
To keep the UI lightweight and modern, this system avoids heavy shadows. Instead, it uses **Tonal Layers** and **Ambient Shadows**:
- **Level 0 (Base):** Off-white background (`#F8FAFC`).
- **Level 1 (Cards/Surface):** White background with a very soft, diffused 10% opacity shadow (`0 4px 6px -1px rgb(0 0 0 / 0.05)`).
- **Level 2 (Interactive/Hover):** Increase shadow slightly and add a subtle 1px border in a lighter neutral shade (`#E2E8F0`).
- **Level 3 (Modals/Popovers):** Deeper shadows with a backdrop blur (Glassmorphism effect) of 8px to keep the user focused on the foreground task.

## Shapes
The shape language is **Rounded**, conveying a friendly and approachable feel. 
- **Standard elements:** (Buttons, Input fields, Checkboxes) use a 0.5rem (8px) radius.
- **Large elements:** (Task cards, Modals) use a 1rem (16px) radius.
- **Full rounding:** Used for "Add Task" buttons and status badges/pills to distinguish them from structural elements.

## Components
- **Buttons:** Primary buttons use a solid blue background with white text. Secondary buttons use a light blue tint (`#EFF6FF`) with blue text. No harsh borders.
- **Task Cards:** Pure white background, 16px rounded corners, and 24px internal padding. Include a subtle transition on hover where the card moves 2px upward.
- **Checkboxes:** Larger than standard (20px x 20px) with a 6px border radius. When checked, they should fill with the secondary green color and a "bounce" animation.
- **Input Fields:** Use a light gray background (`#F1F5F9`) instead of a border in their default state. On focus, transition to a white background with a 2px blue stroke.
- **Chips/Pills:** Used for categories (e.g., "Math", "History"). Low-saturation backgrounds with high-saturation text for readability.
- **Progress Bars:** Thin 8px bars with rounded ends. The track should be a very light gray, and the fill should be a gradient of primary to secondary.