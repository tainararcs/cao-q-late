document.addEventListener('DOMContentLoaded', () => {
  const imagesSrc = [
    'img/dogs1.jpg',
    'img/dogs2.jpg',
    'img/dogs3.jpg',
    'img/dogs4.jpg',
    'img/dogs6.jpg',
    'img/dogs7.jpg',
    'img/dogs8.jpg',
    'img/dogs9.jpg',
    'img/dogs10.jpg',
    'img/dogs.jpg'
  ];
  const container = document.querySelector('.image-container');
  if (!container) return;

  // Remove imagens estáticas do JSP
  container.querySelectorAll('img').forEach(img => img.remove());

  // Cria wrapper do carrossel
  const carousel = document.createElement('div');
  carousel.style.position = 'relative';
  carousel.style.width = '100%';
  carousel.style.height = '100%';
  carousel.style.display = 'flex';
  carousel.style.justifyContent = 'center';
  carousel.style.alignItems = 'center';
  carousel.style.overflow = 'visible';
  container.appendChild(carousel);

  // Cria imagens com efeitos 3D
  const images = imagesSrc.map(src => {
    const img = document.createElement('img');
    img.src = src;
    img.alt = 'Cachorro';
    img.style.position = 'absolute';
    img.style.width = '70%';
    img.style.height = '100%';
    img.style.objectFit = 'cover';
    img.style.borderRadius = '24px';
    img.style.transition = 'all 1s cubic-bezier(0.4, 0, 0.2, 1)';
    img.style.boxShadow = '0 20px 60px rgba(0,0,0,0.25), 0 0 0 1px rgba(255,255,255,0.4) inset';
    img.style.opacity = '0';
    img.style.transformStyle = 'preserve-3d';
    carousel.appendChild(img);
    return img;
  });

  let current = 0;
  const total = images.length;
  function updateCarousel() {
    const prev = (current - 1 + total) % total;
    const next = (current + 1) % total;
    images.forEach((img, i) => {
      img.style.opacity = '0';
      img.style.transform = 'translateX(0) translateZ(-100px) scale(0.7) rotateY(0deg)';
      img.style.zIndex = '0';
      img.style.filter = 'brightness(0.7) blur(2px)';
    });

    // Imagem anterior (esquerda)
    images[prev].style.opacity = '0.6';
    images[prev].style.transform = 'translateX(-200px) translateZ(-50px) scale(0.85) rotateY(15deg)';
    images[prev].style.zIndex = '1';
    images[prev].style.filter = 'brightness(0.8) blur(1px)';
    // Imagem atual (centro) - destaque
    images[current].style.opacity = '1';
    images[current].style.transform = 'translateX(0) translateZ(20px) scale(1.08)';
    images[current].style.zIndex = '5';
    images[current].style.filter = 'brightness(1) blur(0)';
    images[current].style.boxShadow = '0 25px 70px rgba(0,0,0,0.3), 0 0 0 1px rgba(255,255,255,0.5) inset';
    // Imagem seguinte (direita)
    images[next].style.opacity = '0.6';
    images[next].style.transform = 'translateX(200px) translateZ(-50px) scale(0.85) rotateY(-15deg)';
    images[next].style.zIndex = '1';
    images[next].style.filter = 'brightness(0.8) blur(1px)';
  }

  function nextImage() {
    current = (current + 1) % total;
    updateCarousel();
  }

  // Navegação por teclado
  document.addEventListener('keydown', (e) => {
    if (e.key === 'ArrowRight') {
      nextImage();
    } else if (e.key === 'ArrowLeft') {
      current = (current - 1 + total) % total;
      updateCarousel();
    }
  });

  // Click para avançar
  carousel.addEventListener('click', () => {
    nextImage();
  });

  // Adiciona cursor pointer
  carousel.style.cursor = 'pointer';

  // Efeito parallax com mouse
  container.addEventListener('mousemove', (e) => {
    const rect = container.getBoundingClientRect();
    const x = (e.clientX - rect.left - rect.width / 2) / rect.width;
    const y = (e.clientY - rect.top - rect.height / 2) / rect.height;

    images[current].style.transform = `
      translateX(${x * 15}px)
      translateY(${y * 15}px)
      translateZ(20px)
      scale(1.08)
      rotateY(${x * 5}deg)
      rotateX(${-y * 5}deg)
    `;
  });

  container.addEventListener('mouseleave', () => {
    images[current].style.transform = 'translateX(0) translateZ(20px) scale(1.08)';
  });

  updateCarousel();

  // Auto-play mais suave
  setInterval(nextImage, 5000);
});